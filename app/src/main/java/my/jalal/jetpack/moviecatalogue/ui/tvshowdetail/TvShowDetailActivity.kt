package my.jalal.jetpack.moviecatalogue.ui.tvshowdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_tv_show_detail.*
import kotlinx.android.synthetic.main.activity_tv_show_detail.img_photo
import kotlinx.android.synthetic.main.activity_tv_show_detail.progressBar
import kotlinx.android.synthetic.main.activity_tv_show_detail.toggle_favorite
import kotlinx.android.synthetic.main.activity_tv_show_detail.tv_description
import kotlinx.android.synthetic.main.activity_tv_show_detail.tv_name
import kotlinx.android.synthetic.main.activity_tv_show_detail.tv_score
import kotlinx.android.synthetic.main.activity_tv_show_detail.tv_status
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.api.tmdb.TMDBClient
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal
import my.jalal.jetpack.moviecatalogue.viewmodel.ViewModelFactory
import java.util.*

class TvShowDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_tv_show"
    }

    private lateinit var language: String
    private lateinit var tvShowDetailViewModel: TvShowDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showLoading(true)
        val tvShow = intent.getParcelableExtra<TvShowLocal>(EXTRA_DATA)
        val tvShowId = tvShow?.id
        val posterUrl = TMDBClient.POSTER_BASE_URL + tvShow?.posterPath
        language = Locale.getDefault().toLanguageTag()
        val factory = ViewModelFactory.getInstance(this)
        tvShowDetailViewModel = ViewModelProvider(this, factory)[TvShowDetailViewModel::class.java]
        tvShowDetailViewModel.getTv(tvShowId, language).observe(this, androidx.lifecycle.Observer {
            Glide.with(this)
                .load(posterUrl)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_broken_image_black_24dp)
                        .error(R.drawable.ic_broken_image_black_24dp)
                )
                .into(img_photo)
            tv_name.text = it?.name
            tv_score.text = it?.voteAverage.toString()
            tv_status.text = it?.status
            tv_first_air_date.text = it?.firstAirDate
            tv_description.text = it?.overview
            showLoading(false)
        })

        toggle_favorite.textOn = ""
        toggle_favorite.textOff = ""
        toggle_favorite.text = ""
        toggle_favorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                tvShow?.isFavorite = true
                tvShowDetailViewModel.insertFavorite(tvShow as TvShowLocal)
            } else {
                tvShow?.isFavorite = false
                tvShowDetailViewModel.updateFavorite(tvShow as TvShowLocal)
            }
        }

        tvShowDetailViewModel.getTvLocal(tvShow?.id).observe(this, androidx.lifecycle.Observer {
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