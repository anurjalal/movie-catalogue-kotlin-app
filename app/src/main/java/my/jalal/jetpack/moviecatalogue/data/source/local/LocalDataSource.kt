package my.jalal.jetpack.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import my.jalal.jetpack.moviecatalogue.data.source.local.db.CatalogueDao
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal

class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(catalogueDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieLocal> =
        mCatalogueDao.getAllFavoriteMovies()

    fun getAllFavoriteTv(): DataSource.Factory<Int, TvShowLocal> =
        mCatalogueDao.getAllFavoriteTvShows()

    suspend fun insertMovie(movieLocal: MovieLocal) = mCatalogueDao.insert(movieLocal)

    suspend fun insertTvShow(tvShowLocal: TvShowLocal) = mCatalogueDao.insert(tvShowLocal)

    suspend fun updateMovie(movieLocal: MovieLocal) = mCatalogueDao.updateMovie(movieLocal)

    suspend fun updateTv(tvShowLocal: TvShowLocal) = mCatalogueDao.updateTvShow(tvShowLocal)

    fun getMovieLocal(id: Int?): LiveData<MovieLocal?> = mCatalogueDao.getMovieLocal(id)

    fun getTvShowLocal(id: Int?): LiveData<TvShowLocal?> = mCatalogueDao.getTvShowLocal(id)

}