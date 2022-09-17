package xyz.terrifictable.entropy.api

import dev.virefire.viira.application
import xyz.terrifictable.entropy.Defs

class API {

    fun start(port: Int) {

        val app = application()


        Defs.router = Router(app)
        Defs.router.loadRouts()


        app.start(port)
    }


}

