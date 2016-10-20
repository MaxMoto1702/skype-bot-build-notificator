package learn.skype.bot

import groovy.transform.ToString

@ToString(includeSuperProperties = true, includeNames = true, ignoreNulls = true)
class MessageActivity extends Activity {

    String locale
    String text
    String summary
    String textFormat
    String attachmentLayout
//    List<Attachment> attachments
//    List<Entity> Entities
    String replyToId

    static constraints = {
        locale nullable: true
        summary nullable: true
        textFormat nullable: true
        attachmentLayout nullable: true
        replyToId nullable: true
    }

    static mapping = {
        id generator: 'assigned'
    }
}

//{
//    "type": "message",
//    "id": "7d396d5ac56f419da4bb09b6e65f6240",
//    "timestamp": "2016-10-19T14:19:08.434878Z",
//    "serviceUrl": "http://localhost:9000/",
//    "channelId": "emulator",
//    "from": {
//        "id": "617d3bf8",
//        "name": "User1"
//    },
//    "conversation": {
//        "isGroup": false,
//        "id": "1cf91be5",
//        "name": "Conv1"
//    },
//    "recipient": {
//        "id": "5e4f5dfa",
//        "name": "Bot1"
//    },
//    "text": "\u0000test",
//    "attachments": [],
//    "entities": []
//}