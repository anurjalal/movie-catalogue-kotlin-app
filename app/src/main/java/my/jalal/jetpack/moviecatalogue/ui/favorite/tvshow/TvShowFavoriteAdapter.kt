package my.jalal.jetpack.moviecatalogue.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_tv_show.view.*
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.api.tmdb.TMDBClient
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal

class TvShowFavoriteAdapter(private val callback: TvShowFavoriteFragmentCallback) :
    PagedListAdapter<TvShowLocal, TvShowFavoriteAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowLocal>() {
            override fun areItemsTheSame(oldItem: TvShowLocal, newItem: TvShowLocal): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowLocal, newItem: TvShowLocal): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowFavoriteAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_tv_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowFavoriteAdapter.ViewHolder, position: Int) {
        val tvShow = getItem(position) as TvShowLocal
        holder.bind(tvShow)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowLocal) {
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