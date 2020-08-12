package my.jalal.jetpack.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal

class MovieFavoriteViewModel(private val catalogueRepository: CatalogueRepositoryImpl) : ViewModel() {

    fun getMovies(): LiveData<PagedList<MovieLocal>> = catalogueRepository.getFavoriteMovieList()
}