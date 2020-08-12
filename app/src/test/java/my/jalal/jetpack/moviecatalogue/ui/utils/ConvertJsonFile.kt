package my.jalal.jetpack.moviecatalogue.ui.utils

import com.google.gson.Gson
import java.io.File

object ConvertJsonFile {
    fun <T> toEntityObject(pathname:String, entityClass: Class<T>): T {
        val movieTestFile = File(pathname).inputStream()
        val movieResponse = movieTestFile.bufferedReader().use { it.readText() }
        return Gson().fromJson(movieResponse, entityClass)
    }
}