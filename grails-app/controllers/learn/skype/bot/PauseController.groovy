package learn.skype.bot

import grails.converters.JSON

class PauseController {
	static responseFormats = ['json', 'xml']

    def pauseService
	
    def index() {
        render pauseService.get() as JSON
    }
}
