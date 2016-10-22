package learn.skype.bot

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(view: '/index')

        post "/api/messages"(controller: "message", action: 'save')

        post "/api/deploy/init"(controller: "deploy", action: 'init')
        post "/api/deploy/start"(controller: "deploy", action: 'start')
        post "/api/deploy/finish"(controller: "deploy", action: 'finish')

        get "/api/pauses"(controller: "pause", action: 'index')

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
