package xyz.terrifictable.entropy.utils.formatting

import com.google.gson.Gson
import com.google.gson.JsonObject


/**
 * Format JsonObject
 * Input: {"a":"b","c":"d"}
 * Output: {
 *      "a": "b",
 *      "c": "d"
 * }
 *
 * @author TerrificTable
 */
class JsonFormatter(json: JsonObject) {
    var result: String


    init {
        if (json.isJsonNull || json == JsonObject()) {
            result = "{ }"
        } else {

            val gson_obj = Gson().toJson(json)
            val sb = StringBuilder()
            var indent = 0
            for (i in gson_obj.indices) {
                val c = gson_obj[i]
                if (c == '{') {
                    sb.append(c)
                    if (gson_obj[i + 1] == '}') sb.append(" ") else {
                        sb.append("\n")
                        indent++
                        for (j in 0 until indent) sb.append("\t")
                    }
                } else if (c == '}') {
                    if (gson_obj[i - 1] != '{') {
                        sb.append("\n")
                        indent--
                        for (j in 0 until indent) sb.append("\t")
                    }
                    sb.append(c)
                } else if (c == '[') {
                    sb.append(c)
                    if (gson_obj[i + 1] == ']') sb.append(" ") else {
                        sb.append("\n")
                        indent++
                        for (j in 0 until indent) sb.append("\t")
                    }
                } else if (c == ']') {
                    if (gson_obj[i - 1] != '[') {
                        sb.append("\n")
                        indent--
                        for (j in 0 until indent) sb.append("\t")
                    }
                    sb.append(c)
                } else if (c == ',') {
                    sb.append(c)
                    sb.append("\n")
                    for (j in 0 until indent) sb.append("\t")
                } else if (c == ':') {
                    sb.append(c)
                    sb.append(" ")
                } else {
                    sb.append(c)
                }
            }

            result = sb.toString()
        }
    }


}
