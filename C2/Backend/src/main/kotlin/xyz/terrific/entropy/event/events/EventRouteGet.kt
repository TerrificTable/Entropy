package xyz.terrific.entropy.event.events

import dev.virefire.viira.types.Response
import xyz.terrific.entropy.api.Route
import xyz.terrific.entropy.event.Event

class EventRouteGet(private val caller: Route, private var res: Response) : Event<EventRouteGet>(Type.API) {
    fun getCaller(): Route {
        return caller
    }

    fun getRes(): Response {
        return this.res
    }
    fun setRes(new: Response) {
        this.res = new
    }
}
