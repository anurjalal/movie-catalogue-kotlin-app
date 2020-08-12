package my.jalal.jetpack.moviecatalogue.ui.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
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
class MovieDetailViewModelTest {
    private val lotrId = 122
    private val language = "en-US"
    private lateinit var viewModel: MovieDetailViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepositoryImpl

    @Mock
    private lateinit var observer: Observer<Movie>

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovie() {
        val liveDataTestModel = MutableLiveData<Movie>()
        val jsonTestPath = "src/main/assets/test/lotr.json"
        val testModel = ConvertJsonFile.toEntityObject(jsonTestPath, Movie::class.java)
        liveDataTestModel.value = testModel
        `when`(catalogueRepository.getMovieDetail(lotrId, language)).thenReturn(liveDataTestModel)
        val movieResult = viewModel.getMovie(lotrId, language).value as Movie
        verify(catalogueRepository).getMovieDetail(lotrId, language)
        assertNotNull(movieResult)
        assertEquals(testModel.id, movieResult.id)
        assertEquals(testModel.title, movieResult.title)
        assertEquals(testModel.posterPath, movieResult.posterPath)
        assertEquals(testModel.overview, movieResult.overview)

        viewModel.getMovie(lotrId, language).observeForever(observer)
        verify(observer).onChanged(testModel)
    }
}