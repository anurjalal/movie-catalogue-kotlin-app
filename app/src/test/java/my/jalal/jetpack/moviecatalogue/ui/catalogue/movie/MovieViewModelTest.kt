package my.jalal.jetpack.moviecatalogue.ui.catalogue.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.MoviesResponse
import my.jalal.jetpack.moviecatalogue.ui.utils.ConvertJsonFile
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private val language = "en-US"
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepositoryImpl

    @Mock
    private lateinit var observer: Observer<ArrayList<Movie>?>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getMovies() {
        val liveDataTestModel = MutableLiveData<ArrayList<Movie>?>()
        val testModel = ConvertJsonFile.toEntityObject(
            "src/main/assets/test/movie_list.json",
            MoviesResponse::class.java
        ).results
        liveDataTestModel.value = testModel
        `when`(catalogueRepository.getMovieList(language)).thenReturn(liveDataTestModel)
        val movieEntities = viewModel.getMovies(language).value as ArrayList<Movie>
        verify(catalogueRepository).getMovieList(language)

        movieEntities.let {
            it.iterator().forEach { movie ->
                assertNotNull(movie)
            }
        }
        assertEquals(true, movieEntities.size >= 10)
        viewModel.getMovies(language).observeForever(observer)
        verify(observer).onChanged(testModel)
    }
}