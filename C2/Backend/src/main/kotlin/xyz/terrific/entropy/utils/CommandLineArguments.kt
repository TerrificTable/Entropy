package utils

import com.google.gson.JsonArray
import com.google.gson.JsonObject

/**
 * @param args command line arguments
 * @author TerrificTable
 */
class CommandLineArguments(args: Array<String>) {
    private var jsonArgs: JsonObject = JsonObject()

    /**
     * Load args as (GSON-)JsonObject
     */
    init {
        val already = arrayOf("").toMutableList()
        val other = JsonArray()

        for (arg: String in args) {
            if (already.contains(arg)) continue

            if (arg.startsWith("-")) {
                jsonArgs.addProperty(arg, args[args.indexOf(arg) + 1])
                already.add(args[args.indexOf(arg) + 1])
            } else {
                if (!arg.startsWith("-") && arg != " ") {
                    other.add(arg)
                }
            }
        }

        jsonArgs.add("other", other)
    }


    /**
     * Returns requested argument as String
     *
     * @param name the name of the argument
     * @return the requested argument
     */
    fun getArgument(name: String): String {
        return jsonArgs.get(name).asString
    }

    /**
     * Returns requested argument as Int if argument is/can be converted to Int, otherwise returns -1
     *
     * @param name the name of the argument
     * @return the requested argument
     */
    fun getArgumentInt(name: String): Int {
        return try { jsonArgs.get(name).asString.toInt() } catch ( e: Exception ){ -1 }
    }

}

