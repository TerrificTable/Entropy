package xyz.terrifictable.example.extension

import xyz.terrifictable.entropy.Defs
import xyz.terrifictable.entropy.extensions.Extension
import xyz.terrifictable.entropy.utils.Logger
import xyz.terrifictable.example.extension.features.ExampleFeature
import xyz.terrifictable.example.extension.routes.ExampleRoute

class ExampleMain {

    val name: String = "Example"


    @Extension.InitialLoad
    fun initialLoad() {
        Logger.success("$name-Plugin loaded")
    }


    @Extension.FeaturesLoad
    fun loadFeatures() {
        Defs.featureManager.addFeature(ExampleFeature())
    }


    @Extension.RoutesLoad
    fun loadRoutes() {
        Defs.router.addRoute(ExampleRoute())
    }

}
