package xyz.terrific.entropy.api

import dev.virefire.viira.Application
import xyz.terrific.entropy.api.routs.*


class Router(private val app: Application) {

    val routs: ArrayList<Route> = ArrayList()

    init {
        addRout(Root())
        addRout(Test())
    }

    fun addRout(rout: Route) {
        routs.add(rout)
    }


    fun getRout(name: String): Route? {
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

