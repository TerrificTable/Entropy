package xyz.terrifictable.entropy.api

import dev.virefire.viira.Application
import xyz.terrifictable.entropy.api.routs.*
import xyz.terrifictable.entropy.extensions.Extension


class Router(private val app: Application) {

    val routs: ArrayList<Route> = ArrayList()

    init {
        addRoute(Root())
        addRoute(Test())

        Extension.routesLoad()
    }

    fun addRoute(rout: Route) {
        routs.add(rout)
    }


    fun getRoute(name: String): Route? {
        for (rout: Route in routs) {
            if (rout.getName() == name) {
                return rout
            }
        }
        return null
    }


    fun loadRouts() {
        for (rout: Route in routs) {
            rout.rout(this.app)
        }
    }

}

