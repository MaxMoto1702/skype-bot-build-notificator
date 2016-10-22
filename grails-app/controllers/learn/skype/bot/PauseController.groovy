package learn.skype.bot

import grails.rest.*
import grails.converters.*

class PauseController {
	static responseFormats = ['json', 'xml']

    def pauseService
	
    def index() {
        render pauseService.get()
    }
}
