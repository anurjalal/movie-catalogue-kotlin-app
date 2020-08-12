package my.jalal.jetpack.moviecatalogue.ui.catalogue.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl

class MovieViewModel(private val catalogueRepository: CatalogueRepositoryImpl) : ViewModel() {
    fun getMovies(language: String): LiveData<ArrayList<Movie>?> = catalogueRepository.getMovieList(language)
}