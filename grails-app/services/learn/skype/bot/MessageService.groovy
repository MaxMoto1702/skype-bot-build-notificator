package learn.skype.bot

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

@Transactional
class MessageService {

    def rest = new RestBuilder()

    def tokenUrl = 'https://login.microsoftonline.com/common/oauth2/v2.0/token'
    def tokenExpires = 0
    def tokenType
    def token

    def url = 'https://apis.skype.com/v3/conversations/8:maxim.serebryanskiy/activities/'

    def sendMessage(text) {
        if (!token || System.currentTimeSeconds() > tokenExpires) {
            def resp = rest.post(tokenUrl) {
                contentType('application/x-www-form-urlencoded')
                body('client_id=94491a0c-d490-4485-a77f-d476e79bf6b1&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F&grant_type=client_credentials&client_secret=nWy706jOcPGOHVjwZY75DLy')
            }
            tokenType = resp.json.token_type
            token = resp.json.access_token
            tokenExpires = System.currentTimeSeconds() - (resp.json.expires_in as Long) - 60
        }

        def resp = rest.post(url) {
            auth("$tokenType $token")
            json(["type": "message/text","text": "Request: $text"] as JSON)
        }

        resp.json
    }
}
