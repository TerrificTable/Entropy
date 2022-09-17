package xyz.terrifictable.entropy.event.events

import dev.virefire.viira.types.Request
import xyz.terrifictable.entropy.api.Route
import xyz.terrifictable.entropy.event.Event

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
