package my.jalal.jetpack.moviecatalogue.di

import android.content.Context
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.local.LocalDataSource
import my.jalal.jetpack.moviecatalogue.data.source.local.db.CatalogueDatabase
import my.jalal.jetpack.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): CatalogueRepositoryImpl {
        val database = CatalogueDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        return CatalogueRepositoryImpl.getInstance(remoteDataSource, localDataSource)
    }
}