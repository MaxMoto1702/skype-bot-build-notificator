package maxmoto1702.skype.bot.server

import com.microsoft.bot.connector.model.Activity
import com.microsoft.bot.connector.model.Attachment
import com.microsoft.bot.connector.model.CardAction
import com.microsoft.bot.connector.model.HeroCard
import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder

class SkypeService {

    final static ADMIN_ID = '8:maxim.serebryanskiy'
    final static USPN_GROUP_ID = '19:de34912aaee7486fbb46df7b5dbb4048@thread.skype'
    final static SANDBOX_GROUP_ID = '19:b884056990214f63957d4d3fa8444945@thread.skype'

    def authService

    def sendMessage(params) {
        Activity activity = new Activity(
                type: "message/text",
                text: params.text
        )
        send(params.recipient.id, activity)
    }

    def sendHeroCard(params) {
        HeroCard heroCard = new HeroCard(
                title: params.title,
                subtitle: params.subTitle,
                text: params.text,
                buttons: params.actions?.collect {
                    new CardAction(
                            type: it.type,
                            title: it.title,
                            value: it.value
                    )
                }
        )
        Attachment attachment = new Attachment(
                contentType: "application/vnd.microsoft.card.hero",
                content: heroCard
        )
        Activity acivity = new Activity(
                type: "message/card",
                attachments: [attachment]
        )
        send(params.recipient.id, acivity)
    }

    def sendTyping() {
        def activity = new Activity(
                type: "typing"
        )
    }

    def send(recipientId, Activity activity) {
        def rest = new RestBuilder()
        def resp = rest.post("https://apis.skype.com/v3/conversations/$recipientId/activities/") {
            auth(authService.accessToken)
            json(activity as JSON)
        }
        if (resp.status != 201) {
            sendError("<b>Bad request!</b>\n<i>Request</i>: ${(activity as JSON).toString().replaceAll(/[<>]/, '_')}.\n<i>Response</i>: ${resp.status} - ${resp.json}")
        }
    }

    def sendError(text) {
        def rest = new RestBuilder()
        rest.post("https://apis.skype.com/v3/conversations/$ADMIN_ID/activities/") {
            auth(authService.accessToken)
            json(new Activity("type": "message/text", "text": text) as JSON)
        }
    }
}
