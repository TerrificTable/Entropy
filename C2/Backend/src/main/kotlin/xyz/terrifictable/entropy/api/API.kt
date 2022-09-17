package xyz.terrifictable.entropy.api

import dev.virefire.viira.application
import xyz.terrifictable.entropy.Main

class API {

    fun start(port: Int) {

        val app = application()


        Main.router = Router(app)
        Main.router.loadRouts()


        app.start(port)
    }


}

