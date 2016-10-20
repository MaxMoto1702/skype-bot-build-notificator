package learn.skype.bot

import groovy.transform.ToString

@ToString(includeSuperProperties = true, includeNames = true, ignoreNulls = true)
class ConversationAccount {

    Boolean isGroup
    String id
    String name

    static constraints = {
    }

    static mapping = {
        id generator: 'assigned'
    }
}
