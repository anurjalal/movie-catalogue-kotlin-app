package my.jalal.jetpack.moviecatalogue.data.source.remote.entity

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    var page: Int?,
    var results: ArrayList<TvShow>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)
