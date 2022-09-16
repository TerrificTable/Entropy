package feature

import extensions.Extension
import feature.features.TestFeature

class FeatureManager {

    val features: ArrayList<Feature> = ArrayList()

    init {

        addFeature(TestFeature())

        Extension.featuresLoad()

    }


    private fun addFeature(feature: Feature) {
        this.features.add(feature)
    }

}

