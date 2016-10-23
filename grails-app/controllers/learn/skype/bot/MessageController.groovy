package learn.skype.bot

import com.microsoft.bot.connector.model.Activity
import grails.converters.JSON

import static org.springframework.http.HttpStatus.CREATED

class MessageController {

    static responseFormats = ['json']
    static allowedMethods = [post: "POST"]

    def pauseService
    def skypeService

    def post() {
        skypeService.sendTyping()
        def json = JSON.parse(request.reader)
        log.info("Получено сообщение: $json")
        try {
            def activity = new Activity(json as Map)
            log.info("Сообщение преобразовано: $activity")
            def duration = MessageUtils.extractPauseTime(activity.text)
            if (duration) {
                pauseService.add([
                        start   : System.currentTimeSeconds(),
                        expires : System.currentTimeSeconds() + duration,
                        duration: duration,
                        user    : activity.from

                ])
                skypeService.sendMessage(
                        recipient: [id: SkypeService.SANDBOX_GROUP_ID],
                        text: "${activity.from.name}, ваша просьба ваша просьба услышана :)"
                )
            } else {
                skypeService.sendError("<b>Bad receive message!</b>\n<i>Message</i>: ${(activity).toString().replaceAll(/[<>]/, '_')}")
                skypeService.sendMessage(
                        recipient: [id: SkypeService.SANDBOX_GROUP_ID],
                        text: "Я вас не понял"
                )
            }
        } catch (Exception e) {
            log.error("Error in new mechanism", e)
            log.warn("Try old mechanism...")
            def duration = MessageUtils.extractPauseTime(json.text)
            if (duration) {
                pauseService.add([
                        start   : System.currentTimeSeconds(),
                        expires : System.currentTimeSeconds() + duration,
                        duration: duration,
                        user    : json.from

                ])
                skypeService.sendMessage(
                        recipient: [id: SkypeService.SANDBOX_GROUP_ID],
                        text: "${json.from?.name}, ваша просьба ваша просьба услышана :)")
            } else {
                skypeService.sendError("<b>Bad receive message!</b>\n<i>Message</i>: ${(json).toString().replaceAll(/[<>]/, '_')}")
                skypeService.sendMessage(
                        recipient: [id: SkypeService.SANDBOX_GROUP_ID],
                        text: "Я вас не понял"
                )
            }
        }
        render status: CREATED
    }
}
