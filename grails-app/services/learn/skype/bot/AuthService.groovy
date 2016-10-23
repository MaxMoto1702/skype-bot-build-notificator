package learn.skype.bot

import grails.plugins.rest.client.RestBuilder

class AuthService {

    final static CLIENT_ID = '94491a0c-d490-4485-a77f-d476e79bf6b1'
    final static CLIENT_SECRET = 'nWy706jOcPGOHVjwZY75DLy'

    def tokenUrl = 'https://login.microsoftonline.com/common/oauth2/v2.0/token'
    def tokenExpires = 0
    def tokenType
    def token

    def rest = new RestBuilder()

    def getAccessToken() {
        if (!token || System.currentTimeSeconds() > tokenExpires) {
            def resp = rest.post(tokenUrl) {
                contentType('application/x-www-form-urlencoded')
//                body("client_id=$CLIENT_ID&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F&grant_type=client_credentials&client_secret=$CLIENT_SECRET")
                body("client_id=94491a0c-d490-4485-a77f-d476e79bf6b1&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F&grant_type=client_credentials&client_secret=nWy706jOcPGOHVjwZY75DLy")
            }
            if (resp.status == 200) {
                tokenType = resp.json.token_type
                token = resp.json.access_token
                tokenExpires = System.currentTimeSeconds() - (resp.json.expires_in as Long) - 60
            } else {
                log.error("Can not authenticate!!!")
            }
        }
        "$tokenType $token"
    }
}
