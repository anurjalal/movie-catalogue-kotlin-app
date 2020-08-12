package my.jalal.jetpack.moviecatalogue.ui.catalogue.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShowsResponse
import my.jalal.jetpack.moviecatalogue.ui.utils.ConvertJsonFile
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val language = "en-US"

    @Mock
    private lateinit var catalogueRepository: CatalogueRepositoryImpl

    @Mock
    private lateinit var observer: Observer<ArrayList<TvShow>?>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun getMovies() {
        val liveDataTestModel = MutableLiveData<ArrayList<TvShow>?>()
        val testModel = ConvertJsonFile.toEntityObject(
            "src/main/assets/test/tv_list.json",
            TvShowsResponse::class.java
        ).results
        liveDataTestModel.value = testModel
        `when`(catalogueRepository.getTvShowList(language)).thenReturn(liveDataTestModel)
        val tvShowEntities = viewModel.getTvShows(language).value as ArrayList<TvShow>
        verify(catalogueRepository).getTvShowList(language)

        tvShowEntities.let {
            it.iterator().forEach { tvShow ->
                assertNotNull(tvShow)
            }
        }

        assertEquals(true, tvShowEntities.size >= 10)
        viewModel.getTvShows(language).observeForever(observer)
        verify(observer).onChanged(testModel)

    }
}