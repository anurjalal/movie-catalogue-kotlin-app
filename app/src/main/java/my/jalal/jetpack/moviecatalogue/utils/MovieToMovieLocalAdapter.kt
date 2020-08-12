package my.jalal.jetpack.moviecatalogue.utils

import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie

class MovieToMovieLocalAdapter (movieRemote: Movie){
    private val id = movieRemote.id
    private val overview = movieRemote.overview
    private val posterPath = movieRemote.posterPath
    private val releaseDate = movieRemote.releaseDate
    private val status = movieRemote.status
    private val title = movieRemote.title
    private val voteAverage = movieRemote.voteAverage

    fun getMovieLocal(): MovieLocal {
        return MovieLocal(id, title, releaseDate, overview, posterPath, status, voteAverage, false)
    }
}