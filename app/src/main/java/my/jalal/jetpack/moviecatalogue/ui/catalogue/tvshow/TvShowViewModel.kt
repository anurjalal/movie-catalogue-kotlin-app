package my.jalal.jetpack.moviecatalogue.ui.catalogue.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl

class TvShowViewModel(private val catalogueRepository: CatalogueRepositoryImpl) : ViewModel() {

    fun getTvShows(language: String): LiveData<ArrayList<TvShow>?> = catalogueRepository.getTvShowList(language)

}