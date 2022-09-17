package xyz.terrific.entropy.api.routs

import dev.virefire.viira.Application
import xyz.terrific.entropy.api.Route
import xyz.terrific.entropy.event.events.EventRouteGet
import xyz.terrific.entropy.event.events.EventRoutePost
import xyz.terrific.entropy.utils.Logger


class Test : Route("test", Type.BOTH) {

    override fun rout(app: Application) {

        app.get(this.getName()) {
            EventRouteGet(Test(), res)
            res.text("Hello World!")
        }

        app.post(this.getName()) {
            EventRoutePost(Test(), req)
            Logger.info("${req.headers.entries} -> ${req.json}", "Rout->Test")
        }

    }

}
