package xyz.terrific.entropy

import xyz.terrific.entropy.api.API
import xyz.terrific.entropy.api.Router
import xyz.terrific.entropy.feature.FeatureManager

class Defs {
    companion object {
        lateinit var featureManager: FeatureManager
        lateinit var router: Router
        val api: API = API()
    }
}
