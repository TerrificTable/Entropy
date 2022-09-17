package xyz.terrifictable.entropy.feature.features

import xyz.terrifictable.entropy.event.Event
import xyz.terrifictable.entropy.event.events.EventRouteGet
import xyz.terrifictable.entropy.feature.Feature
import xyz.terrifictable.entropy.utils.Logger


class TestFeature : Feature("Test", "This features is there to test features", Category.OTHER) {

    init {
        Logger.info("TestFeature Loaded")
    }


    override fun onEvent(event: Event<*>) {
        if (event is EventRouteGet) {
            Logger.info(event.javaClass.simpleName)
        }
    }

    override fun impl() {

        println("Test!")

    }

}

