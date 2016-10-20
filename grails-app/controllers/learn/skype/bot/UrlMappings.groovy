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

        get "/api/messages"(controller: "message", action: 'index')
        post "/api/messages"(controller: "message", action: 'save')

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
