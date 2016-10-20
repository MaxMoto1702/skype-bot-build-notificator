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

    def index(Integer max) {
        render messageService.sendMessage()
    }

    @Transactional
    def save() {
//        def parse = JSON.parse(request.reader)
        log.debug("Request data: ${parse}")
//        def message = new MessageActivity(parse as Map)
//        message.id = parse.id
//        message.from = new ChannelAccount(parse.from as Map)
//        message.from.id = parse.from.id
//        message.conversation = new ConversationAccount(parse.conversation as Map)
//        message.conversation.id = parse.conversation.id
//        message.recipient = new ChannelAccount(parse.recipient as Map)
//        message.recipient.id = parse.recipient.id
//        message.save(flush: true)
//        log.info("MessageActivity: $message")
        messageService.sendMessage(request.reader.text)
        render status: CREATED
    }
}
