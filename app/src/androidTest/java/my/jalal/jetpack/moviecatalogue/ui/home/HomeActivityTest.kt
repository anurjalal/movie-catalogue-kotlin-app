package my.jalal.jetpack.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.MoviesResponse
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShowsResponse
import my.jalal.jetpack.moviecatalogue.ui.home.utils.FileConverter
import my.jalal.jetpack.moviecatalogue.utils.EspressoIdlingResource
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    private lateinit var movieTestModel: ArrayList<Movie>
    private lateinit var tvShowTestModel: ArrayList<TvShow>

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        val moviesResponseObject =
            FileConverter.convertJsonFileToEntityObject(
                "test/movie_list.json",
                MoviesResponse::class.java
            )
        movieTestModel = moviesResponseObject.results as ArrayList<Movie>
        val tvResponseObject =
            FileConverter.convertJsonFileToEntityObject(
                "test/tv_list.json",
                TvShowsResponse::class.java
            )
        tvShowTestModel = tvResponseObject.results as ArrayList<TvShow>

    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovieList() {
        onView(withId(R.id.btn_catalogue)).perform(click())
        onView(withText(R.string.movie)).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movieTestModel.size - 1
            )
        )
    }

    @Test
    fun loadTvList() {
        onView(withId(R.id.btn_catalogue)).perform(click())
        onView(withText(R.string.tvshow)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShowTestModel.size - 1
            )
        )
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.btn_catalogue)).perform(click())
        onView(withText(R.string.movie)).perform(click())
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.img_photo)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvDetail() {
        onView(withId(R.id.btn_catalogue)).perform(click())
        onView(withText(R.string.tvshow)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.first_air_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_first_air_date)).check(matches(isDisplayed()))
        onView(withId(R.id.img_photo)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))

    }

    @Test
    fun insertFavoriteMovie(){
        onView(withId(R.id.btn_catalogue)).perform(click())
        onView(withText(R.string.movie)).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.toggle_favorite)).check(matches(isNotChecked())).perform(click())
        pressBack()
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withText(R.string.movie)).perform(click())
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.img_photo)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
    }

    @Test
    fun insertFavoriteTvShow(){
        onView(withId(R.id.btn_catalogue)).perform(click())
        onView(withText(R.string.tvshow)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.toggle_favorite)).check(matches(isNotChecked())).perform(click())
        pressBack()
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withText(R.string.tvshow)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.first_air_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_first_air_date)).check(matches(isDisplayed()))
        onView(withId(R.id.img_photo)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
    }

}