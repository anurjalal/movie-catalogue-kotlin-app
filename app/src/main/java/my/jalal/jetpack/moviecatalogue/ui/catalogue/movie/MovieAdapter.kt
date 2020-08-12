package my.jalal.jetpack.moviecatalogue.ui.catalogue.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_movie.view.*
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.api.tmdb.TMDBClient
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie

class MovieAdapter(private val callback: MovieFragmentCallback) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listMovies = ArrayList<Movie?>()

    fun setData(items: ArrayList<Movie>?) {
        listMovies.clear()
        items?.let { listMovies.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie? = listMovies[position]
        movie?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
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
