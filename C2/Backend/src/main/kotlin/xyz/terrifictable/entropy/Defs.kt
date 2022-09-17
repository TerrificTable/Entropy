package xyz.terrifictable.entropy

import xyz.terrifictable.entropy.api.API
import xyz.terrifictable.entropy.api.Router
import xyz.terrifictable.entropy.feature.FeatureManager


class Defs {
    companion object {
        lateinit var featureManager: FeatureManager
        lateinit var router: Router
        val api: API = API()
    }
}
