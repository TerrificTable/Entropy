package xyz.terrific.entropy.api.routs

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dev.virefire.viira.Application
import xyz.terrific.entropy.api.Route
import xyz.terrific.entropy.event.events.EventRouteGet
import xyz.terrific.entropy.utils.formatting.JsonFormatter

class Root : Route("Root", Type.GET) {

    override fun rout(app: Application) {
        app.get("/") {
            EventRouteGet(Root(), res)

            val test: JsonObject = JsonObject()
            test.addProperty("a", "b")
            test.addProperty("c", "d")
            test.addProperty("e", "f")
            val json: JsonArray = JsonArray()
            json.add("hi1")
            json.add("hi2")
            json.add("hi3")
            json.add("hi4")
            test.add("test", json)

            res.text(JsonFormatter(test).result)
        }
    }

}
