import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

def targetDir = BuildSettings.TARGET_DIR
def userHome = "/opt/tomcat8"
if (Environment.isDevelopmentMode() && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
    root(INFO, ['STDOUT', 'FULL_STACKTRACE'])
}
else {
    appender("FILE", FileAppender) {
        file = "${userHome}/logs/skype-bot.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    root(INFO, ['STDOUT', 'FILE'])
}




