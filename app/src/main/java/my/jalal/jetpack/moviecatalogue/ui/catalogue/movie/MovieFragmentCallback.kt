package my.jalal.jetpack.moviecatalogue.ui.catalogue.movie

import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie

interface MovieFragmentCallback {
    fun onShareClick(movie: Movie){

    }
}