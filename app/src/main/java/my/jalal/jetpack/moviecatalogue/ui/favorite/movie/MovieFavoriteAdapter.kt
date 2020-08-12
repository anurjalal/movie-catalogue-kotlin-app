package my.jalal.jetpack.moviecatalogue.ui.favorite.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_movie.view.*
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.api.tmdb.TMDBClient
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal

class MovieFavoriteAdapter(private val callback: MovieFavoriteFragmentCallback) :
    PagedListAdapter<MovieLocal, MovieFavoriteAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieLocal>() {
            override fun areItemsTheSame(oldItem: MovieLocal, newItem: MovieLocal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieLocal, newItem: MovieLocal): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieFavoriteAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position) as MovieLocal
        holder.bind(movie)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieLocal) {
            with(itemView) {
                tv_name.text = movie.title
                tv_score.text = movie.voteAverage.toString()
                tv_description.text = movie.overview
                this.setOnClickListener { callback.onShareClick(movie) }
                Glide.with(context)
                    .load(TMDBClient.POSTER_BASE_URL + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_broken_image_black_24dp)
                            .error(R.drawable.ic_broken_image_black_24dp)
                    )
                    .into(img_photo)
            }
        }
    }
}
