package my.jalal.jetpack.moviecatalogue.utils

import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow

class TvToTvLocalAdapter(tvShowRemote: TvShow) {
    private var firstAirDate = tvShowRemote.firstAirDate
    private var id = tvShowRemote.id
    private var name = tvShowRemote.name
    private var overview = tvShowRemote.overview
    private var posterPath = tvShowRemote.posterPath
    private var status = tvShowRemote.status
    private var voteAverage = tvShowRemote.voteAverage

    fun getTvLocal(): TvShowLocal{
        return TvShowLocal(id, name, firstAirDate, overview, posterPath, status, voteAverage, false)
    }
}