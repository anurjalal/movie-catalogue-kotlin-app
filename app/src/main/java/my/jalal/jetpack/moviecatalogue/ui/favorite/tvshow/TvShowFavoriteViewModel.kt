package my.jalal.jetpack.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal

class TvShowFavoriteViewModel(private val catalogueRepository: CatalogueRepositoryImpl) : ViewModel() {

    fun getTvShows(): LiveData<PagedList<TvShowLocal>> = catalogueRepository.getFavoriteTvShowList()

}