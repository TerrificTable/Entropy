package feature.features

import feature.Feature
import utils.Logger


class TestFeature : Feature("Test", "This features is there to test features", Category.OTHER) {

    init {
        Logger.info("TestFeature Loaded")
    }


    override fun impl() {

        println("Test!")

    }

}

