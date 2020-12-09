package scripts

import scripts.dax_api.api_lib.models.DaxCredentials
import scripts.dax_api.api_lib.models.DaxCredentialsProvider
import java.io.File
import java.nio.file.Files
import java.util.*

class DaxConfigs(private val filePath: String): DaxCredentialsProvider() {

    private val defaultKeys = "apiKey=sub_DPjXXzL5DeSiPf\nsecretKey=PUBLIC-KEY"

    override fun getDaxCredentials(): DaxCredentials {
        val file = loadFile(filePath)
        val properties = Properties()
        properties.load(file.bufferedReader())
        return DaxCredentials(properties["apiKey"] as String, properties["secretKey"] as String)
    }

    private fun loadFile(filePath: String): File {
        val file = File(filePath)
        if(!file.exists())
            createDefault(file)
        return file
    }

    private fun createDefault(file: File) {
        file.parentFile.mkdir()
        file.createNewFile()
        Files.write(file.toPath(), defaultKeys.toByteArray())
    }
}