package my.jalal.jetpack.moviecatalogue.api.tmdb

import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.MoviesResponse
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiEndpoints {

    @GET("3/discover/movie")
    fun getMovieList(
        @Query("api_key") API_KEY: String?,
        @Query("language") language: String?
    ): Call<MoviesResponse?>?

    @GET("3/discover/tv")
    fun getTvShowList(
        @Query("api_key") API_KEY: String?,
        @Query("language") language: String?
    ): Call<TvShowsResponse?>?


    @GET("3/movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int?,
        @Query("api_key") API_KEY: String?,
        @Query("language") language: String?
    ): Call<Movie?>?

    @GET("3/tv/{id}")
    fun getTvShowDetail(
        @Path("id") id: Int?,
        @Query("api_key") API_KEY: String?,
        @Query("language") language: String?
    ): Call<TvShow?>?
}
