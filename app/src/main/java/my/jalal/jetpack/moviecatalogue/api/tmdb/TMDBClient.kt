package my.jalal.jetpack.moviecatalogue.api.tmdb

import my.jalal.jetpack.moviecatalogue.api.RetrofitClient

class TMDBClient(connectionTimeout: Long, readTimeout: Long) {

    private val retrofitClient: RetrofitClient =
        RetrofitClient(
            BASE_URL,
            connectionTimeout,
            readTimeout
        )

    private val api = retrofitClient.getRetrofit().create(TMDBApiEndpoints::class.java)

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val API_KEY = "813c1dbd60adaf1c82cdccc9d53d0bf1"
        const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185/"
    }

    fun getObject(): TMDBApiEndpoints {
        return api
    }

}