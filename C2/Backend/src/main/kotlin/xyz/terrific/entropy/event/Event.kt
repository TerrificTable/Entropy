package xyz.terrific.entropy.event

import xyz.terrific.entropy.Defs
import xyz.terrific.entropy.feature.Feature

open class Event<T>(type: Type) {

    init {
        this.run()
    }


    fun run() {
        for (f: Feature in xyz.terrific.entropy.Defs.featureManager.features) {
            f.onEvent(this)
        }
    }


    enum class Type {
        API,
        INTERNAL,
        OTHER
    }
}
