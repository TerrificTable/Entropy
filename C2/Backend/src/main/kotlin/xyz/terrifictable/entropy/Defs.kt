package xyz.terrifictable.entropy

import xyz.terrifictable.entropy.api.API
import xyz.terrifictable.entropy.api.Router
import xyz.terrifictable.entropy.data.DB
import xyz.terrifictable.entropy.feature.FeatureManager
import java.sql.Connection


class Defs {
    companion object {
        lateinit var featureManager: FeatureManager
        lateinit var router: Router

        var dbConn: Connection? = DB().connect("", "", "")
        val api: API = API()
    }
}
