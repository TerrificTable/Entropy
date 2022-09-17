package xyz.terrifictable.example.extension.routes

import xyz.terrifictable.entropy.api.Route
import xyz.terrifictable.entropy.event.events.EventRouteGet

class ExampleRoute : Route("Example", Type.GET) {

    override fun rout(app: dev.virefire.viira.Application) {

        app.get("/example") {

            EventRouteGet(ExampleRoute(), res)
            res.send("<h1></h1><hr><a href=\"https://github.com/TerrificTable/Entropy/tree/master/C2/Example-Extension\">Link</a>".toByteArray())

        }

    }

}
