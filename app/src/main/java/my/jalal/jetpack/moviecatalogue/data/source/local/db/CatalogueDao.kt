package my.jalal.jetpack.moviecatalogue.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal

@Dao
interface CatalogueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieLocal: MovieLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShowLocal: TvShowLocal)

    @Update
    suspend fun updateMovie(movieLocal: MovieLocal)

    @Update
    suspend fun updateTvShow(tvShowLocal: TvShowLocal)

    @Query("SELECT * FROM movies WHERE is_favorite=1")
    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieLocal>

    @Query("SELECT * FROM tv_shows WHERE is_favorite=1")
    fun getAllFavoriteTvShows(): DataSource.Factory<Int, TvShowLocal>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieLocal(id:Int?) : LiveData<MovieLocal?>

    @Query("SELECT * FROM tv_shows WHERE id = :id")
    fun getTvShowLocal(id:Int?) : LiveData<TvShowLocal?>
}