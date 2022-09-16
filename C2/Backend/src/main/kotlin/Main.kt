import extensions.Extension
import feature.FeatureManager
import utils.CommandLineArguments
import utils.Logger

fun main(args: Array<String>) {
    val args = CommandLineArguments(args)

    Extension.initialLoad()
    FeatureManager()

    Logger.info("Test", "MainKt")
}

