package xyz.terrific.entropy.event.events

import dev.virefire.viira.types.Request
import xyz.terrific.entropy.api.Route
import xyz.terrific.entropy.event.Event

class EventRoutePost(private val caller: Route, private var req: Request) : Event<EventRoutePost>(Type.API) {
    fun getCaller(): Route {
        return caller
    }

    fun getReq(): Request {
        return this.req
    }
    fun setReq(new: Request) {
        this.req = new
    }
}
