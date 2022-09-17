package xyz.terrific.entropy.api

import dev.virefire.viira.application
import xyz.terrific.entropy.Defs

class API {

    fun start(port: Int) {

        val app = application()


        xyz.terrific.entropy.Defs.router = Router(app)
        xyz.terrific.entropy.Defs.router.loadRouts()


        app.start(port)
    }


}

