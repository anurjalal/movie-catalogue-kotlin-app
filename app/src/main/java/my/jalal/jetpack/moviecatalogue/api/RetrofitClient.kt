package my.jalal.jetpack.moviecatalogue.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient(
    baseUrl: String,
    connectionTimeout: Long = 5,
    readTimeout: Long = 5
) {
    private val retrofit: Retrofit
    init {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
        retrofit = builder.build()
    }

    fun getRetrofit(): Retrofit{
        return retrofit
    }

}