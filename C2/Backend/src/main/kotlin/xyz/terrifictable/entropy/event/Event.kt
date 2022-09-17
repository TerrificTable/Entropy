package xyz.terrifictable.entropy.event

import xyz.terrifictable.entropy.Main
import xyz.terrifictable.entropy.feature.Feature

open class Event<T>(type: Type) {

    init {
        this.run()
    }


    fun run() {
        for (f: Feature in Main.featureManager.features) {
            f.onEvent(this)
        }
    }


    enum class Type {
        API,
        INTERNAL,
        OTHER
    }
}
