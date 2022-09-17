package xyz.terrifictable.entropy.extensions

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import xyz.terrifictable.entropy.utils.JsonUtils
import java.io.*
import java.util.*
import java.util.jar.JarEntry
import java.util.jar.JarFile


enum class ExtensionManager {
    INSTANCE;
    private val plugins: ArrayList<Extension> = ArrayList<Extension>()


    fun add(name: String, version: String, clazz: Class<*>, description: String, authors: Array<String?>): Extension {
        val extension = Extension(clazz, Extension.ExtensionInfo(name, version, description, authors))
        getPlugins().add(extension)
        return extension
    }


    fun loadExtensions() {
        val pluginsFolder = File(System.getProperty("user.home"), ".c2${File.separator}extensions")
        if (!pluginsFolder.exists()) {
            pluginsFolder.mkdirs()
            return
        }

        try {
            val inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream("extension.json")
            if (inputStream != null) {
                val jsonObject: JsonObject = JsonUtils.convertStringToJsonObject(read(inputStream))
                loadFromJson(jsonObject, null, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        for (file in pluginsFolder.listFiles()!!) {
            try {
                if (file.name.endsWith(".jar")) {
                    val jarFile = JarFile(file)
                    val entries: Enumeration<JarEntry> = jarFile.entries()

                    while (entries.hasMoreElements()) {
                        val entry = entries.nextElement()
                        if (!entry.isDirectory && Regex(".*.extension.json").matches(entry.name)) {
                            val inputStream = jarFile.getInputStream(entry)
                            val jsonObject: JsonObject = JsonUtils.convertStringToJsonObject(read(inputStream))
                            loadFromJson(jsonObject, file, jarFile)
                            break
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        Collections.shuffle(getPlugins())
        println(String.format("Loaded Extension: %d\n", getPlugins().size))
        getPlugins().forEach { extension ->
            println("- ${extension.getInfo().name} v$extension.getInfo().getVersion()",)
        }
    }

    @Throws(IOException::class)
    private fun read(inputStream: InputStream): String {
        val sb = StringBuilder()
        try {
            val streamReader = InputStreamReader(inputStream, "UTF-8")
            val reader = BufferedReader(streamReader)
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                sb.append(line).append("\n")
            }
        } catch (e: NullPointerException) {
        }
        return sb.toString()
    }

    @Throws(ClassNotFoundException::class)
    private fun loadFromJson(jsonObject: JsonObject, fileOfJar: File?, jarFile: JarFile?) {
        val name = jsonObject["name"].asString
        val version = jsonObject["version"].asString
        val mainClass = jsonObject["mainClass"].asString
        val description = jsonObject["description"].asString
        val jsonArray = jsonObject.getAsJsonArray("authors")
        val list = ArrayList<String>()
        jsonArray.forEach { jsonElement: JsonElement -> list.add(jsonElement.asString) }


        sanityCheckFiles(mainClass, jarFile)
        // if (fileOfJar != null) FabricLauncherBase.getLauncher().addToClassPath(fileOfJar.toPath())
        add(name, version, Class.forName(mainClass), description, toArray(list))
    }

    private fun sanityCheckFiles(mainClass: String, jarFile: JarFile?) {
        val mainClassEntry = jarFile?.getJarEntry(mainClass.replace(".", "/") + ".class")
            ?: throw RuntimeException("Main class $mainClass not found! Plugin will not be loaded!")
    }

    private fun toArray(list: ArrayList<String>): Array<String?> {
        val array = arrayOfNulls<String>(list.size)
        for (i in list.indices) {
            array[i] = list[i]
        }
        return array
    }

    operator fun get(name: String): Extension? {
        for (plugin in plugins) {
            if (plugin.getInfo().name == name) return plugin
        }
        return null
    }

    fun getPlugins(): ArrayList<Extension> {
        return plugins
    }
}
