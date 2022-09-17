package xyz.terrifictable.entropy.api.routs

import dev.virefire.viira.Application
import xyz.terrifictable.entropy.api.Route
import xyz.terrifictable.entropy.event.events.EventRouteGet
import xyz.terrifictable.entropy.event.events.EventRoutePost
import xyz.terrifictable.entropy.utils.Logger


class Test : Route("test", Type.BOTH) {

    override fun rout(app: Application) {

        app.get(this.getName()) {
            EventRouteGet(Test(), res)
            res.text("Hello World!") // .send -> send a file to download
        }

        app.post(this.getName()) {
            EventRoutePost(Test(), req)
            Logger.info("${req.headers.entries} -> ${req.json}", "Rout->Test")
        }

    }

}
