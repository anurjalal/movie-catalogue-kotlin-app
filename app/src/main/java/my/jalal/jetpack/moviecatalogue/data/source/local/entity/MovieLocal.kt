package my.jalal.jetpack.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MovieLocal(
    @PrimaryKey
    var id: Int?,
    var title: String?,
    @ColumnInfo(name="release_data")
    var releaseDate: String?,
    var overview: String?,
    @ColumnInfo(name = "poster_path")
    var posterPath: String?,
    var status: String?,
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double?,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean?
) : Parcelable