package xyz.terrifictable.entropy

import xyz.terrifictable.entropy.feature.FeatureManager
import xyz.terrifictable.entropy.utils.CommandLineArguments
import xyz.terrifictable.entropy.utils.Logger
import xyz.terrifictable.entropy.extensions.Extension
import xyz.terrifictable.entropy.extensions.ExtensionManager

fun main(args: Array<String>) {
    val args = CommandLineArguments(args)
    ExtensionManager.INSTANCE.loadExtensions()


    Extension.initialLoad()
    FeatureManager()

    Logger.info("Test", "MainKt")
}

