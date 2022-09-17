package xyz.terrifictable.example.extension.features

import xyz.terrifictable.entropy.event.Event
import xyz.terrifictable.entropy.event.events.EventRouteGet
import xyz.terrifictable.entropy.feature.Feature
import xyz.terrifictable.entropy.utils.Logger

class ExampleFeature : Feature("Example", "This is an example feature", Category.OTHER) {

    override fun onEvent(event: Event<*>) {
        if (event is EventRouteGet) {
            Logger.info(event.getCaller().getName())
        }

    }

    override fun impl() {

    }

}
