package my.jalal.jetpack.moviecatalogue.data.source.remote.entity

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    var page: Int?,
    var results: ArrayList<Movie>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)
