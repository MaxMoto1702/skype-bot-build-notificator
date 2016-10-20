package learn.skype.bot

import groovy.transform.ToString

@ToString(includeSuperProperties = true, includeNames = true, ignoreNulls = true)
class Activity {

    String type
    String id
    String serviceUrl
    String timestamp
    String channelId
    ChannelAccount from
    ConversationAccount conversation
    ChannelAccount recipient

    static constraints = {
        type nullable: true
        serviceUrl nullable: true
        timestamp nullable: true
        channelId nullable: true
        from nullable: true
        conversation nullable: true
        recipient nullable: true
    }

    static mapping = {
        id generator: 'assigned'
    }
}
