package maxmoto1702.skype.bot.server

import grails.converters.JSON

class PauseController {
	static responseFormats = ['json', 'xml']

    def pauseService
	
    def index() {
        render pauseService.get() as JSON
    }
}
