package xyz.terrifictable.entropy.feature

import xyz.terrifictable.entropy.feature.features.TestFeature

class FeatureManager {

    val features: ArrayList<Feature> = ArrayList()

    init {

        addFeature(TestFeature())

    }


    fun addFeature(feature: Feature) {
        this.features.add(feature)
    }

}

