package learn.skype.bot

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional

@Transactional
class MessageService {

    final static CLIENT_ID = '94491a0c-d490-4485-a77f-d476e79bf6b1'
    final static CLIENT_SECRET = 'nWy706jOcPGOHVjwZY75DLy'
//    final static USPN_GROUP_ID  = '19:de34912aaee7486fbb46df7b5dbb4048@thread.skype'
    final static SANDBOX_GROUP_ID = '19:b7de55213c9c4eaea28cad7fb8dd75f9@thread.skype'

    def pauseService

    def rest = new RestBuilder()

    def tokenUrl = 'https://login.microsoftonline.com/common/oauth2/v2.0/token'
    def tokenExpires = 0
    def tokenType
    def token

    def receiveMessage(params) {
        log.info("$params")
        if (params.text =~ /^подожди (\d*) минут, пожалуйста/) {
            def duration = ((params.text =~ /подожди (\d+) минут, пожалуйста/)[0][1] as Long) * 60
            pauseService.add([
                    start   : System.currentTimeSeconds(),
                    expires : System.currentTimeSeconds() + duration,
                    duration: duration,
                    user    : params.user

            ])
        } else {
            activateToken()
            rest.post('https://apis.skype.com/v3/conversations/8:maxim.serebryanskiy/activities/') {
                auth("$tokenType $token")
                json([
                        "type": "message/text",
                        "text": "Bad receive message! " +
                                "Message: ${(params as JSON).toString().replaceAll(/[<>]/, '_')}"
                ] as JSON)
            }
        }
    }

    def sendMessage(text) {
        log.info("Send message: $text")
        def content = [
                "type": "message/text",
                "text": text.replaceAll(/[<>]/, '_')
        ]
        send([
                recipient: SANDBOX_GROUP_ID,
                content  : content
        ])
    }

    def sendInitCard() {
        def waitFiveMinutes = [
                "type" : "imBack",
                "title": "Пауза на 5 минут",
                "value": "подожди 5 минут, пожалуйста"
        ]
        def waitTenMinutes = [
                "type" : "imBack",
                "title": "Пауза на 10 минут",
                "value": "подожди 10 минут, пожалуйста"
        ]
        def waitFifteenMinutes = [
                "type" : "imBack",
                "title": "Пауза на 15 минут",
                "value": "подожди 15 минут, пожалуйста"
        ]
        def buttons = [
                waitFiveMinutes,
                waitTenMinutes,
                waitFifteenMinutes
        ]
        def attachmentContent = [
                "title"   : "Через 5 минут будет установка на DEV-стенде",
                "subtitle": "Для приостановки на некоторое время, нажмите соотвествующую кнопку ниже",
                "buttons" : buttons
        ]
        def attachment = [
                "contentType": "application/vnd.microsoft.card.hero",
                "content"    : attachmentContent
        ]
        def attachments = [
                attachment
        ]
        def content = [
                "type"       : "message/card",
                "attachments": attachments
        ]
        send([
                recipient: SANDBOX_GROUP_ID,
                content  : content
        ])
    }

    private def send(params) {
        activateToken()
        def resp = rest.post("https://apis.skype.com/v3/conversations/${params.recipient}/activities/") {
            auth("$tokenType $token")
            json(params.content as JSON)
        }
        if (resp.status != 201) {
            resp = rest.post('https://apis.skype.com/v3/conversations/8:maxim.serebryanskiy/activities/') {
                auth("$tokenType $token")
                json([
                        "type": "message/text",
                        "text": "Bad request! " +
                                "Request: ${(params.content as JSON).toString().replaceAll(/[<>]/, '_')}" +
                                "Response: ${resp.status} - ${resp.json}"
                ] as JSON)
            }
        }
    }

    private def activateToken() {
        if (!token || System.currentTimeSeconds() > tokenExpires) {
            def resp = rest.post(tokenUrl) {
                contentType('application/x-www-form-urlencoded')
//                body("client_id=$CLIENT_ID&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F&grant_type=client_credentials&client_secret=$CLIENT_SECRET")
                body("client_id=94491a0c-d490-4485-a77f-d476e79bf6b1&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F&grant_type=client_credentials&client_secret=nWy706jOcPGOHVjwZY75DLy")
            }
            tokenType = resp.json.token_type
            token = resp.json.access_token
            tokenExpires = System.currentTimeSeconds() - (resp.json.expires_in as Long) - 60
        }

    }
}
