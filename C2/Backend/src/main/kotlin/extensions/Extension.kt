package extensions

import java.lang.reflect.InvocationTargetException


/**
 * Credits: thanks @DustinRepo->JexClient, for a "reference" plugin system
 */
class Extension(private var mainClass: Class<*>, private var info: ExtensionInfo) {
    private var mainClassInstance: Any? = null

    companion object {
        fun initialLoad() {
            ExtensionManager.INSTANCE.getPlugins().forEach { extension ->
                val mainClass: Class<*> = extension.getMainClassInstance()!!::class.java

                for (declaredMethod in mainClass.declaredMethods) {
                    if (declaredMethod.isAnnotationPresent(ClientLoad::class.java)) {
                        try {
                            declaredMethod.invoke(extension.getMainClassInstance())
                        } catch (e: IllegalAccessException) {
                            e.printStackTrace()
                        } catch (e: InvocationTargetException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        fun featuresLoad() {
            ExtensionManager.INSTANCE.getPlugins().forEach { extension ->
                val mainClass: Class<*> = extension.getMainClassInstance()!!::class.java

                for (declaredMethod in mainClass.declaredMethods) {
                    if (declaredMethod.isAnnotationPresent(FeaturesLoad::class.java)) {
                        try {
                            declaredMethod.invoke(extension.getMainClassInstance())
                        } catch (e: IllegalAccessException) {
                            e.printStackTrace()
                        } catch (e: InvocationTargetException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }


    fun getMainClass(): Class<*> {
        return mainClass
    }

    fun getInfo(): ExtensionInfo {
        return info
    }

    fun getMainClassInstance(): Any? {
        if (mainClassInstance == null) {
            try {
                mainClassInstance = getMainClass().newInstance()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        return mainClassInstance
    }



    @Retention(AnnotationRetention.RUNTIME)
    annotation class FeaturesLoad
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ClientLoad


    class ExtensionInfo(
        val name: String,
        val version: String,
        val description: String,
        val authors: Array<String?>
    )

}

