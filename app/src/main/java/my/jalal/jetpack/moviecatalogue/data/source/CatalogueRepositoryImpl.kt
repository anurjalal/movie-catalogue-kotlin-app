package my.jalal.jetpack.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import my.jalal.jetpack.moviecatalogue.data.source.local.LocalDataSource
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal
import my.jalal.jetpack.moviecatalogue.data.source.remote.RemoteDataSource
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow

class CatalogueRepositoryImpl private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    CatalogueRepository {
    companion object {
        @Volatile
        private var instance: CatalogueRepositoryImpl? = null
        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): CatalogueRepositoryImpl =
            instance
                ?: synchronized(this) {
                    instance
                        ?: CatalogueRepositoryImpl(remoteDataSource, localDataSource)
                }
    }


    override fun getMovieList(language: String): LiveData<ArrayList<Movie>?> =
        remoteDataSource.getMovieList(language)

    override fun getTvShowList(language: String): LiveData<ArrayList<TvShow>?> =
        remoteDataSource.getTvShowList(language)

    override fun getMovieDetail(id: Int?, language: String): LiveData<Movie> =
        remoteDataSource.getMovieDetail(id, language)

    override fun getTvDetail(id: Int?, language: String): LiveData<TvShow> =
        remoteDataSource.getTvDetail(id, language)

    override fun getFavoriteMovieList(): LiveData<PagedList<MovieLocal>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShowList(): LiveData<PagedList<TvShowLocal>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteTv(), config).build()
    }

    override fun getMovieLocal(id: Int?): LiveData<MovieLocal?> = localDataSource.getMovieLocal(id)

    override suspend fun updateMovie(movieLocal: MovieLocal) = localDataSource.updateMovie(movieLocal)

    override suspend fun updateTv(tvShowLocal: TvShowLocal) = localDataSource.updateTv(tvShowLocal)

    override suspend fun insertFavoriteTv(tvShowLocal: TvShowLocal) = localDataSource.insertTvShow(tvShowLocal)

    override fun getTvShowLocal(id: Int?): LiveData<TvShowLocal?> = localDataSource.getTvShowLocal(id)

    override suspend fun insertFavoriteMovie(movieLocal: MovieLocal) = localDataSource.insertMovie(movieLocal)


}