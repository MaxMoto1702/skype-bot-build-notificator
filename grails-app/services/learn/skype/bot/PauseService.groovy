package learn.skype.bot

class PauseService {

    def pauses = []

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
