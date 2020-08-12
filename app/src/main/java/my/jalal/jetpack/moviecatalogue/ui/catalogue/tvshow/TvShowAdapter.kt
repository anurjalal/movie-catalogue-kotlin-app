package my.jalal.jetpack.moviecatalogue.ui.catalogue.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_tv_show.view.*
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.api.tmdb.TMDBClient
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow

class TvShowAdapter(private val callback: TvShowFragmentCallback) :
    RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private var listTvShows = ArrayList<TvShow?>()

    fun setData(items: ArrayList<TvShow>?) {
        listTvShows.clear()
        items?.let { listTvShows.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_tv_show, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listTvShows.size

    override fun onBindViewHolder(holder: TvShowAdapter.ViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        tvShow?.let { holder.bind(it) }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShow) {
            with(itemView) {
                tv_name.text = tvShow.name
                tv_score.text = tvShow.voteAverage.toString()
                tv_description.text = tvShow.overview
                Glide.with(context)
                    .load(TMDBClient.POSTER_BASE_URL + tvShow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_broken_image_black_24dp)
                            .error(R.drawable.ic_broken_image_black_24dp)
                    )
                    .into(img_photo)
                this.setOnClickListener { callback.onShareClick(tvShow) }
            }
        }
    }
}