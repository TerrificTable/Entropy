package xyz.terrific.entropy.feature.features

import xyz.terrific.entropy.event.Event
import xyz.terrific.entropy.event.events.EventRouteGet
import xyz.terrific.entropy.feature.Feature
import xyz.terrific.entropy.utils.Logger


class TestFeature : Feature("Test", "This features is there to test features", Category.OTHER) {

    init {
        Logger.info("TestFeature Loaded")
    }


    override fun onEvent(event: Event<*>) {
        if (event is EventRouteGet) {
            Logger.info("$event")
        }
    }

    override fun impl() {

        println("Test!")

    }

}

