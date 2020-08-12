package my.jalal.jetpack.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tv_shows")
data class TvShowLocal(
    @PrimaryKey
    var id: Int?,
    var name: String?,
    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String?,
    var overview: String?,
    @ColumnInfo(name = "poster_path")
    var posterPath: String?,
    var status: String?,
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double?,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean?
) : Parcelable