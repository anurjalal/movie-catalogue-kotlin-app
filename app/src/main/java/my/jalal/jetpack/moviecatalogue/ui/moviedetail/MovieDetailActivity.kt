package my.jalal.jetpack.moviecatalogue.ui.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_movie_detail.*
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.api.tmdb.TMDBClient
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal
import my.jalal.jetpack.moviecatalogue.viewmodel.ViewModelFactory
import java.util.*

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_movie"
    }

    private lateinit var language: String
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showLoading(true)
        val movie = intent.getParcelableExtra<MovieLocal>(EXTRA_DATA)
        val movieId = movie?.id
        val posterUrl = TMDBClient.POSTER_BASE_URL + movie?.posterPath
        language = Locale.getDefault().toLanguageTag()
        val factory = ViewModelFactory.getInstance(this)
        movieDetailViewModel = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]
        movieDetailViewModel.getMovie(movieId, language).observe(this, androidx.lifecycle.Observer {
            Glide.with(this)
                .load(posterUrl)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_broken_image_black_24dp)
                        .error(R.drawable.ic_broken_image_black_24dp)
                )
                .into(img_photo)
            tv_name.text = it?.title
            tv_score.text = it?.voteAverage.toString()
            tv_status.text = it?.status
            tv_release_date.text = it?.releaseDate
            tv_description.text = it?.overview
            showLoading(false)
        })

        toggle_favorite.textOn = ""
        toggle_favorite.textOff = ""
        toggle_favorite.text = ""
        toggle_favorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                movie?.isFavorite = true
                movieDetailViewModel.insertFavorite(movie as MovieLocal)
            } else {
                movie?.isFavorite = false
                movieDetailViewModel.updateFavorite(movie as MovieLocal)
            }
        }

        movieDetailViewModel.getMovieLocal(movie?.id).observe(this, androidx.lifecycle.Observer {
            val isFavorite = it?.isFavorite ?: false
            Log.d("ININI", isFavorite.toString())
            if(isFavorite){
                toggle_favorite.isChecked = true
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}