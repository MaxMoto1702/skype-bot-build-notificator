package learn.skype.bot

import grails.converters.JSON
import groovy.util.logging.Slf4j

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Slf4j
@Transactional(readOnly = true)
class MessageController {

    static responseFormats = ['json']
    static allowedMethods = [save: "POST"]

    def messageService

    @Transactional
    def save() {
        def json = JSON.parse(request.reader)
        messageService.receiveMessage(text: json.text, user: json.from)
        render status: CREATED
    }
}
