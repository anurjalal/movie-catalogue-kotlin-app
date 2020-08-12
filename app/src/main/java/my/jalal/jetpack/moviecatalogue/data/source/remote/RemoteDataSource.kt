package my.jalal.jetpack.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import my.jalal.jetpack.moviecatalogue.api.tmdb.TMDBClient
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.MoviesResponse
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShowsResponse
import my.jalal.jetpack.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }

        var connectionTimeout = 5L
        var readTimeout = 5L
    }

    fun getMovieList(language: String): LiveData<ArrayList<Movie>?> {
        val listMovies = MutableLiveData<ArrayList<Movie>?>()
        val tmdbApi = TMDBClient(
            connectionTimeout,
            readTimeout
        ).getObject()
        val call = tmdbApi.getMovieList(TMDBClient.API_KEY, language)
        EspressoIdlingResource.increment()
        call?.enqueue(object : Callback<MoviesResponse?> {
            override fun onResponse(
                call: Call<MoviesResponse?>,
                response: Response<MoviesResponse?>
            ) {
                if (!response.isSuccessful) {
                    Log.d("Failed load movie: ", response.code().toString())
                    return
                }
                listMovies.postValue(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse?>, t: Throwable) {
                Log.d("Failed get Data: ", t.message.toString())
            }
        })
        return listMovies
    }

    fun getTvShowList(language: String): LiveData<ArrayList<TvShow>?> {
        val listTvShows = MutableLiveData<ArrayList<TvShow>?>()
        val tmdbApi = TMDBClient(
            connectionTimeout,
            readTimeout
        ).getObject()
        val call = tmdbApi.getTvShowList(TMDBClient.API_KEY, language)
        EspressoIdlingResource.increment()
        call?.enqueue(object : Callback<TvShowsResponse?> {
            override fun onResponse(
                call: Call<TvShowsResponse?>,
                response: Response<TvShowsResponse?>
            ) {
                if (!response.isSuccessful) {
                    Log.d("Failed load tvshow: ", response.code().toString())
                    return
                }
                listTvShows.postValue(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse?>, t: Throwable) {
                Log.d("Failed get Data: ", t.message.toString())
            }
        })
        return listTvShows
    }

    fun getMovieDetail(id: Int?, language: String): LiveData<Movie> {
        val movieMutableLiveData = MutableLiveData<Movie>()
        val tmdbClient = TMDBClient(
            connectionTimeout,
            readTimeout
        )
        val call = tmdbClient.getObject().getMovieDetail(id, TMDBClient.API_KEY, language)
        EspressoIdlingResource.increment()
        call?.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                if (!response.isSuccessful) {
                    Log.d("Failed load movie: ", response.code().toString())
                    return
                }
                val movie = response.body()
                movieMutableLiveData.postValue(movie)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                Log.d("Failed get Data: ", t.message.toString())
            }
        })
        return movieMutableLiveData
    }

    fun getTvDetail(id: Int?, language: String): LiveData<TvShow> {
        val tvMutableLiveData = MutableLiveData<TvShow>()
        val tmdbClient = TMDBClient(
            connectionTimeout,
            readTimeout
        )
        val call = tmdbClient.getObject().getTvShowDetail(id, TMDBClient.API_KEY, language)
        EspressoIdlingResource.increment()
        call?.enqueue(object : Callback<TvShow?> {
            override fun onResponse(call: Call<TvShow?>, response: Response<TvShow?>) {
                if (!response.isSuccessful) {
                    Log.d("Faild load tvShow: ", response.code().toString())
                    return
                }
                val tvShow = response.body()
                tvMutableLiveData.postValue(tvShow)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShow?>, t: Throwable) {
                Log.d("Failed get data: ", t.message.toString())
            }
        })
        return tvMutableLiveData
    }

}