package my.jalal.jetpack.moviecatalogue.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal

class MovieDetailViewModel(private val catalogueRepository: CatalogueRepositoryImpl) : ViewModel() {

    fun getMovie(id: Int?, language: String): LiveData<Movie> =
        catalogueRepository.getMovieDetail(id, language)

    fun insertFavorite(movieLocal: MovieLocal) {
        viewModelScope.launch {
            catalogueRepository.insertFavoriteMovie(movieLocal)
        }
    }

    fun updateFavorite(movieLocal: MovieLocal) {
        viewModelScope.launch {
            catalogueRepository.updateMovie(movieLocal)
        }
    }


    fun getMovieLocal(id: Int?): LiveData<MovieLocal?> = catalogueRepository.getMovieLocal(id)
}