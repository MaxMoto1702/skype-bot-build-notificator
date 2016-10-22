package learn.skype.bot


import grails.rest.*
import grails.converters.*

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
    }

    def start() {
        pauseService.reset()
        messageService.sendMessage("Установка началась...")
    }

    def finish() {
        pauseService.reset()
        messageService.sendMessage("Установка завершена.")
    }
}
