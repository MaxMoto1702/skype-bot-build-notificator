package learn.skype.bot

import grails.transaction.Transactional

@Transactional
class PauseService {

    def pauses

    def reset() {
        pauses = []
    }

    def add(params) {
        pauses << params
        params
    }

    def get() {
        def _ = pauses
        reset()
        _
    }
}
