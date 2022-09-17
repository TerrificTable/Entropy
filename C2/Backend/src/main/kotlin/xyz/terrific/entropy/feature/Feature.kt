package xyz.terrific.entropy.feature

import xyz.terrific.entropy.event.Event

abstract class Feature(var name: String, var description: String, var category: Category) {


    open fun onEvent(event: Event<*>) {  }

    abstract fun impl()




    enum class Category(name: String) {

        C2("C2"),
        CLIENT("Client"),
        SERVER("Server"),
        OTHER("Other")

    }
}
