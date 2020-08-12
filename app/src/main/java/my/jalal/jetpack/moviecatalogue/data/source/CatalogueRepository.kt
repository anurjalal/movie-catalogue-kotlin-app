package my.jalal.jetpack.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow

interface CatalogueRepository {

    fun getMovieList(language : String): LiveData<ArrayList<Movie>?>

    fun getTvShowList(language: String): LiveData<ArrayList<TvShow>?>

    fun getMovieDetail(id: Int?, language: String): LiveData<Movie>

    fun getTvDetail(id: Int?, language: String): LiveData<TvShow>

    fun getFavoriteMovieList() : LiveData<PagedList<MovieLocal>>

    fun getFavoriteTvShowList(): LiveData<PagedList<TvShowLocal>>

    fun getMovieLocal(id: Int?): LiveData<MovieLocal?>

    suspend fun updateMovie(movieLocal: MovieLocal)

    suspend fun updateTv(tvShowLocal: TvShowLocal)

    suspend fun insertFavoriteTv(tvShowLocal: TvShowLocal)

    fun getTvShowLocal(id: Int?): LiveData<TvShowLocal?>

    suspend fun insertFavoriteMovie(movieLocal: MovieLocal)

}