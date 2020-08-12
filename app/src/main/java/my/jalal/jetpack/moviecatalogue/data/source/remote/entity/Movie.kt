package my.jalal.jetpack.moviecatalogue.data.source.remote.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var adult: Boolean?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    var id: Int?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("original_title")
    var originalTitle: String?,
    var overview: String?,
    var popularity: Double?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    var status: String?,
    var title: String?,
    var video: Boolean?,
    @SerializedName("vote_average")
    var voteAverage: Double?,
    @SerializedName("vote_count")
    var voteCount: Int?
) : Parcelable
