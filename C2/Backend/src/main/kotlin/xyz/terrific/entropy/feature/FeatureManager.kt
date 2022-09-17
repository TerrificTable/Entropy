package xyz.terrific.entropy.feature

import xyz.terrific.entropy.extensions.Extension
import xyz.terrific.entropy.feature.features.TestFeature

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

