package xyz.terrifictable.entropy.event.events

import dev.virefire.viira.types.RequestContext
import dev.virefire.viira.types.Response
import xyz.terrifictable.entropy.api.Route
import xyz.terrifictable.entropy.event.Event

class EventRoute(private val caller: Route, private var res: Response, private var req: RequestContext) : Event<EventRoute>(Type.API) {
    fun getCaller(): Route {
        return caller
    }

    fun getRes(): Response {
        return this.res
    }
    fun setRes(new: Response) {
        this.res = new
    }
    fun getReq(): RequestContext {
        return this.req
    }
    fun setReq(new: RequestContext) {
        this.req = new
    }

}
