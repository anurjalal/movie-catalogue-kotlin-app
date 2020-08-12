package my.jalal.jetpack.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import org.mockito.Mockito.verify
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.local.LocalDataSource
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal
import my.jalal.jetpack.moviecatalogue.data.source.remote.RemoteDataSource
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.MoviesResponse
import my.jalal.jetpack.moviecatalogue.ui.utils.PagedListUtil
import my.jalal.jetpack.moviecatalogue.ui.utils.ConvertJsonFile
import my.jalal.jetpack.moviecatalogue.utils.MovieToMovieLocalAdapter
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {

    private lateinit var viewModel: MovieFavoriteViewModel
    private var testModel: ArrayList<MovieLocal> = ArrayList()

    @Mock
    private lateinit var remoteDataSource:RemoteDataSource

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepositoryImpl

    @Mock
    private lateinit var local: LocalDataSource

    @Before
    fun setUp() {
        viewModel = MovieFavoriteViewModel(catalogueRepository)
        catalogueRepository = CatalogueRepositoryImpl.getInstance(remoteDataSource, local)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getFavoriteMovies() {
        val model = ConvertJsonFile.toEntityObject(
            "src/main/assets/test/movie_list.json",
            MoviesResponse::class.java
        ).results
        if (model != null) {
            for (movie in model) {
                testModel.add(MovieToMovieLocalAdapter(movie).getMovieLocal())
            }
        }
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieLocal>
        `when`(local.getAllFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoriteMovieList()
        val pagedList = PagedListUtil.mockPagedList(testModel)
        verify(local).getAllFavoriteMovies()
        assertNotNull(pagedList)
        assertEquals(true, pagedList.size >= 10)
    }
}