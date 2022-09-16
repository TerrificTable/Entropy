package utils

import com.google.gson.Gson
import com.google.gson.JsonObject


/**
 * @author TerrificTable
 */
object JsonUtils {
    /**
     * Converts String to JsonObject
     * @param json_str the Json String to be returned as JsonObject
     * @return JsonObject
     */
    fun convertStringToJsonObject(json_str: String): JsonObject {
        return Gson().fromJson(json_str, JsonObject::class.java)
    }

}