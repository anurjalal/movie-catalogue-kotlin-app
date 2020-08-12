package my.jalal.jetpack.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.jalal.jetpack.moviecatalogue.di.Injection
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.ui.catalogue.movie.MovieViewModel
import my.jalal.jetpack.moviecatalogue.ui.moviedetail.MovieDetailViewModel
import my.jalal.jetpack.moviecatalogue.ui.catalogue.tvshow.TvShowViewModel
import my.jalal.jetpack.moviecatalogue.ui.favorite.movie.MovieFavoriteViewModel
import my.jalal.jetpack.moviecatalogue.ui.favorite.tvshow.TvShowFavoriteViewModel
import my.jalal.jetpack.moviecatalogue.ui.tvshowdetail.TvShowDetailViewModel

class ViewModelFactory private constructor(private val mCatalogueRepository: CatalogueRepositoryImpl) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
                MovieDetailViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> {
                TvShowDetailViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowFavoriteViewModel::class.java) -> {
                TvShowFavoriteViewModel(mCatalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}