package xyz.terrifictable.entropy.utils

import java.util.Date
import java.util.regex.Pattern

object Logger {
    var ap = Pattern.compile(".*\\.").matcher(Thread.currentThread().stackTrace[2].className).replaceAll("");


    /**
     * @param message Sends: "[`AP`] ( {GREEN} `INFO`)   message"
     */
    fun success(message: String?) {
        System.out.printf("[%s] (\u001b[92mINFO\u001b[0m) '${Date().toLocaleString()}'  |  %s\n", ap, message)
    }
    fun success(message: String?, ap: String) {
        System.out.printf("[%s] (\u001b[92mINFO\u001b[0m) '${Date().toLocaleString()}'  |  %s\n", ap, message)
    }

    /**
     * @param message Sends: "[`AP`] ( {GRAY} `INFO`)   message"
     */
    fun info(message: String?) {
        System.out.printf("[%s] (\u001b[90mINFO\u001b[0m) '${Date().toLocaleString()}'  |  %s\n", ap, message)
    }
    fun info(message: String?, ap: String) {
        System.out.printf("[%s] (\u001b[90mINFO\u001b[0m) '${Date().toLocaleString()}'  |  %s\n", ap, message)
    }

    /**
     * @param message Sends: "[`AP`] ( {YELLOW} `WARNING`)   message"
     */
    fun warning(message: String?) {
        System.out.printf("[%s] (\u001b[93mWARNING\u001b[0m) '${Date().toLocaleString()}'  |  %s\n", ap, message)
    }
    fun warning(message: String?, ap: String) {
        System.out.printf("[%s] (\u001b[93mWARNING\u001b[0m) '${Date().toLocaleString()}'  |  %s\n", ap, message)
    }

    /**
     * @param message Sends: "[`AP`] ( {RED} `ERROR`)   message"
     */
    fun error(message: String?) {
        System.out.printf("[%s] (\u001b[91mERROR\u001b[0m) '${Date().toLocaleString()}'  |  %s\n", ap, message)
    }
    fun error(message: String?, ap: String) {
        System.out.printf("[%s] (\u001b[91mERROR\u001b[0m) '${Date().toLocaleString()}'  |  %s\n", ap, message)
    }
}

