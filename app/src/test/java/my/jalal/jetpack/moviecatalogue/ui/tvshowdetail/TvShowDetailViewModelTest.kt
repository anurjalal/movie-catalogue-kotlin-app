package my.jalal.jetpack.moviecatalogue.ui.tvshowdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import my.jalal.jetpack.moviecatalogue.data.source.CatalogueRepositoryImpl
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow
import my.jalal.jetpack.moviecatalogue.ui.utils.ConvertJsonFile
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowDetailViewModelTest {
    private val mrRobotId = 62560
    private val language = "en-US"
    private lateinit var viewModel: TvShowDetailViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepositoryImpl

    @Mock
    private lateinit var observer: Observer<TvShow>

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(catalogueRepository)
    }

    @Test
    fun getTv() {
        val liveDataTestModel = MutableLiveData<TvShow>()
        val testModel = ConvertJsonFile.toEntityObject(
            "src/main/assets/test/mr_robot.json",
            TvShow::class.java
        )
        liveDataTestModel.value = testModel
        Mockito.`when`(catalogueRepository.getTvDetail(mrRobotId, language))
            .thenReturn(liveDataTestModel)
        val tvResult = viewModel.getTv(mrRobotId, language).value as TvShow
        Mockito.verify(catalogueRepository).getTvDetail(mrRobotId, language)
        Assert.assertNotNull(tvResult)
        Assert.assertEquals(testModel.id, tvResult.id)
        Assert.assertEquals(testModel.name, tvResult.name)
        Assert.assertEquals(testModel.posterPath, tvResult.posterPath)
        Assert.assertEquals(testModel.overview, tvResult.overview)

        viewModel.getTv(mrRobotId, language).observeForever(observer)
        Mockito.verify(observer).onChanged(testModel)
    }
}