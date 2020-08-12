package my.jalal.jetpack.moviecatalogue.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.local.LocalDataSource
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal
import my.jalal.jetpack.moviecatalogue.data.source.remote.RemoteDataSource
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShowsResponse
import my.jalal.jetpack.moviecatalogue.ui.utils.PagedListUtil
import my.jalal.jetpack.moviecatalogue.ui.utils.ConvertJsonFile
import my.jalal.jetpack.moviecatalogue.utils.TvToTvLocalAdapter
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowFavoriteViewModelTest {

    private lateinit var viewModel: TvShowFavoriteViewModel
    private var testModel: ArrayList<TvShowLocal> = ArrayList()

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepositoryImpl

    @Mock
    private lateinit var local: LocalDataSource

    @Before
    fun setUp() {
        viewModel = TvShowFavoriteViewModel(catalogueRepository)
        catalogueRepository = CatalogueRepositoryImpl.getInstance(remoteDataSource, local)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getFavoriteTvShows() {
        val model = ConvertJsonFile.toEntityObject(
            "src/main/assets/test/tv_list.json",
            TvShowsResponse::class.java
        ).results
        if (model != null) {
            for (tv in model) {
                testModel.add(TvToTvLocalAdapter(tv).getTvLocal())
            }
        }
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowLocal>
        Mockito.`when`(local.getAllFavoriteTv()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoriteTvShowList()
        val pagedList = PagedListUtil.mockPagedList(testModel)
        Mockito.verify(local).getAllFavoriteTv()
        assertNotNull(pagedList)
        assertEquals(true, pagedList.size >= 10)
    }
}