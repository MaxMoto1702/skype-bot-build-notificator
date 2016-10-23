package learn.skype.bot

import grails.converters.JSON

import static org.springframework.http.HttpStatus.OK

class DeployController {

    static responseFormats = ['json', 'xml']

    def pauseService
    def skypeService

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
        skypeService.sendHeroCard(
                recipient: [id: SkypeService.SANDBOX_GROUP_ID],
                title: "Через 5 минут будет установка на DEV-стенде",
                subTitle: "Для приостановки на некоторое время, нажмите соотвествующую кнопку ниже",
                actions: [5, 10, 15].collect { duration ->
                    [type : "imBack",
                     title: "Подождать $duration минут",
                     value: "Подожди $duration минут, пожалуйста"]
                }
        )
        render status: OK
    }

    def start() {
        pauseService.reset()
        skypeService.sendHeroCard(
                recipient: [id: SkypeService.SANDBOX_GROUP_ID],
                title: "Установка началась",
                subTitle: "Об окончании установки будет сообщено"
        )
        render status: OK
    }

    def finish() {
        def json = JSON.parse(request.reader)
        def revision = json.revision
        def commits = json.commits
        pauseService.reset()
        skypeService.sendHeroCard(
                recipient: [id: SkypeService.SANDBOX_GROUP_ID],
                title: "Установка завершена",
                subTitle: "Обновление выполнено по ревизию $revision",
        )
        if (commits) {
            skypeService.sendMessage(
                    recipient: [id: SkypeService.SANDBOX_GROUP_ID],
                    text:  "<b>В сборку вошло (commits)</b>:\n" + commits.collect {
                " - $it\n"
            }.sum().toString())
        }
        render status: OK
    }
}
