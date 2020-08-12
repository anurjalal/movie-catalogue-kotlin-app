package my.jalal.jetpack.moviecatalogue.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal

@Database(entities = [(MovieLocal::class), (TvShowLocal::class)], version = 1, exportSchema = false)
abstract class CatalogueDatabase : RoomDatabase() {
    abstract fun catalogueDao(): CatalogueDao
    companion object {
        @Volatile
        private var INSTANCE: CatalogueDatabase? = null
        fun getInstance(context: Context): CatalogueDatabase {
            if (INSTANCE == null) {
                synchronized(CatalogueDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CatalogueDatabase::class.java, "Catalogue.db"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE as CatalogueDatabase
        }
    }
}