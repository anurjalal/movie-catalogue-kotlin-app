package my.jalal.jetpack.moviecatalogue.ui.home

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import my.jalal.jetpack.moviecatalogue.data.source.local.db.CatalogueDao
import my.jalal.jetpack.moviecatalogue.data.source.local.db.CatalogueDatabase
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow
import my.jalal.jetpack.moviecatalogue.ui.home.utils.FileConverter
import my.jalal.jetpack.moviecatalogue.ui.home.utils.LiveDataTestUtil
import my.jalal.jetpack.moviecatalogue.utils.MovieToMovieLocalAdapter
import my.jalal.jetpack.moviecatalogue.utils.TvToTvLocalAdapter
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatalogueDaoTest {
    private lateinit var database: CatalogueDatabase
    private lateinit var catalogueDao: CatalogueDao

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context

        try {
            database = Room.inMemoryDatabaseBuilder(context, CatalogueDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        } catch (e: Exception) {
            Log.i(this.javaClass.simpleName, e.message.toString())
        }
        catalogueDao = database.catalogueDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertFavoriteMovie() {
        val favoriteMovies = catalogueDao.getAllFavoriteMovies()
        val dataList: LiveData<PagedList<MovieLocal>> =
            LivePagedListBuilder(favoriteMovies, 20).build()
        val movie = FileConverter.convertJsonFileToEntityObject("test/lotr.json", Movie::class.java)
        val movieLocal = MovieToMovieLocalAdapter(movie).getMovieLocal()
        movieLocal.isFavorite = true
        runBlocking {
            catalogueDao.insert(movieLocal)
        }
        val testData = LiveDataTestUtil.getValue(dataList).size
        Assert.assertEquals(1, testData)
    }

    @Test
    fun insertFavoriteTvShows(){
        val favoriteTvShows = catalogueDao.getAllFavoriteTvShows()
        val dataList: LiveData<PagedList<TvShowLocal>> =
            LivePagedListBuilder(favoriteTvShows, 20).build()
        val tvShow = FileConverter.convertJsonFileToEntityObject("test/mr_robot.json", TvShow::class.java)
        val tvShowLocal = TvToTvLocalAdapter(tvShow).getTvLocal()
        tvShowLocal.isFavorite = true
        runBlocking {
            catalogueDao.insert(tvShowLocal)
        }
        val testData = LiveDataTestUtil.getValue(dataList).size
        Assert.assertEquals(1, testData)
    }
}