package my.jalal.jetpack.moviecatalogue.ui.favorite.movie

import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal

interface MovieFavoriteFragmentCallback {
    fun onShareClick(movie: MovieLocal){

    }
}