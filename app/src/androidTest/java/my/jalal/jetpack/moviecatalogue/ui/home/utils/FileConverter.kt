package my.jalal.jetpack.moviecatalogue.ui.home.utils

import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson

object FileConverter {
    fun <T> convertJsonFileToEntityObject(
        pathnameInAssets: String,
        entityClass: Class<T>
    ): T {
        val movieTestFile =
            InstrumentationRegistry.getInstrumentation().targetContext.assets.open(
                pathnameInAssets
            )
        val movieResponse = movieTestFile.bufferedReader().use { it.readText() }
        return Gson().fromJson(movieResponse, entityClass)
    }
}