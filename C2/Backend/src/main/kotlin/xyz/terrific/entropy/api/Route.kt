package xyz.terrific.entropy.api

import dev.virefire.viira.Application


abstract class Route(private var name: String, private var type: Type) {
    abstract fun rout(app: Application)

    fun getName(): String {
        return name
    }
    fun getType(): Type {
        return type
    }


    enum class Type {
        POST,
        GET,
        BOTH
    }
}

