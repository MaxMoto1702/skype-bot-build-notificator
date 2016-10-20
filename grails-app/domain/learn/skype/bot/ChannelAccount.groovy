package learn.skype.bot

import groovy.transform.ToString

@ToString(includeSuperProperties = true, includeNames = true, ignoreNulls = true)
class ChannelAccount {

    String id
    String name

    static constraints = {
    }

    static mapping = {
        id generator: 'assigned'
    }
}
