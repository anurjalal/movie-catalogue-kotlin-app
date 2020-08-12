package my.jalal.jetpack.moviecatalogue.ui.tvshowdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal

class TvShowDetailViewModel(private val catalogueRepository: CatalogueRepositoryImpl) : ViewModel() {

    fun getTv(id: Int?, language: String): LiveData<TvShow> = catalogueRepository.getTvDetail(id, language)

    fun insertFavorite(tvShowLocal: TvShowLocal) {
        viewModelScope.launch {
            catalogueRepository.insertFavoriteTv(tvShowLocal)
        }
    }

    fun updateFavorite(tvShowLocal: TvShowLocal) {
        viewModelScope.launch {
            catalogueRepository.updateTv(tvShowLocal)
        }
    }


    fun getTvLocal(id: Int?): LiveData<TvShowLocal?> = catalogueRepository.getTvShowLocal(id)
}