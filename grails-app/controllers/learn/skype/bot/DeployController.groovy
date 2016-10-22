package learn.skype.bot


import grails.rest.*
import grails.converters.*

import static org.springframework.http.HttpStatus.OK

class DeployController {
	static responseFormats = ['json', 'xml']

    def pauseService
    def messageService

    def init() {
        pauseService.reset()
        pauseService.add([
                start   : System.currentTimeSeconds(),
                expires : System.currentTimeSeconds() + 300,
                duration: 300,
                user    : [
                        id: 'deployer',
                        name: 'Deployer'
                ]

        ])
        messageService.sendInitCard()
        render status: OK
    }

    def start() {
        pauseService.reset()
        messageService.sendMessage("Установка началась...")
        render status: OK
    }

    def finish() {
        pauseService.reset()
        messageService.sendMessage("Установка завершена.")
        render status: OK
    }
}
