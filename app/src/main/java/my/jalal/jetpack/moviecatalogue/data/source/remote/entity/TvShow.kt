package my.jalal.jetpack.moviecatalogue.data.source.remote.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("first_air_date")
    var firstAirDate: String?,
    var id: Int?,
    var name: String?,
    @SerializedName("origin_country")
    var originCountry: List<String?>?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("original_name")
    var originalName: String?,
    var overview: String?,
    var popularity: Double?,
    @SerializedName("poster_path")
    var posterPath: String?,
    var status: String?,
    @SerializedName("vote_average")
    var voteAverage: Double?,
    @SerializedName("vote_count")
    var voteCount: Int?
) : Parcelable
