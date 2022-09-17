package xyz.terrifictable.entropy.feature

import xyz.terrifictable.entropy.extensions.Extension
import xyz.terrifictable.entropy.feature.Feature
import xyz.terrifictable.entropy.feature.features.TestFeature

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

