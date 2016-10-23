package maxmoto1702.skype.bot.server

class MessageUtils {
    final static EXTRACT_PAUSE_TIME_REGEX = ~/Подожди (\d*) минут, пожалуйста/

    static extractPauseTime(text) {
        if (text && text =~ EXTRACT_PAUSE_TIME_REGEX) {
            def duration = ((text =~ EXTRACT_PAUSE_TIME_REGEX)[0][1] as Long) * 60
            return duration
        } else {
            return null
        }
    }
}
