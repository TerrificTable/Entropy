package xyz.terrifictable.entropy

import xyz.terrifictable.entropy.api.API
import xyz.terrifictable.entropy.api.Router
import xyz.terrifictable.entropy.extensions.Extension
import xyz.terrifictable.entropy.extensions.ExtensionManager
import xyz.terrifictable.entropy.feature.FeatureManager
import xyz.terrifictable.entropy.utils.CommandLineArguments
import xyz.terrifictable.entropy.utils.Logger
import java.util.*
import kotlin.random.asKotlinRandom

object Main {

    lateinit var featureManager: FeatureManager
    lateinit var router: Router
    val api: API = API()



    @JvmStatic
    fun main(args: Array<String> ) {
        val args = CommandLineArguments(args)
        Logger.info("Starting", "MainKt")
        ExtensionManager.INSTANCE.loadExtensions()


        Extension.initialLoad()
        featureManager = FeatureManager()



        val port = clamp(Random().asKotlinRandom().nextInt(65535), 0, 65535)
        Logger.info("API started on: http://localhost:$port", "MainKt")
        api.start(port)
    }



    /**
     * Simple clamp function, IDK why I didn't use the Maths library or something like that
     */
    @JvmStatic
    fun clamp(value: Int, min: Int, max: Int): Int {
        return if (value < min) min
        else if (value > max)   max
        else                    value
    }

}


