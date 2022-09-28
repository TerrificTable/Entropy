package xyz.terrifictable.entropy.api

import dev.virefire.viira.Application
import xyz.terrifictable.entropy.api.routs.Root
import xyz.terrifictable.entropy.api.routs.Test
import xyz.terrifictable.entropy.extensions.Extension


class Router(private val app: Application) {

    val routes: ArrayList<Route> = ArrayList()

    init {
        addRoute(Root())
        addRoute(Test())
    }

    fun addRoute(rout: Route) {
        routes.add(rout)
    }


    fun getRoute(name: String): Route? {
        for (rout: Route in routes) {
            if (rout.getName() == name) {
                return rout
            }
        }
        return null
    }


    fun loadRouts() {
        Extension.routesLoad()

        for (route: Route in routes) {
            if (!exists(routes, route))
                route.rout(this.app)
        }
    }

    private fun exists(list: ArrayList<Route>, route: Route): Boolean {
        return list.contains(route)
    }

}

