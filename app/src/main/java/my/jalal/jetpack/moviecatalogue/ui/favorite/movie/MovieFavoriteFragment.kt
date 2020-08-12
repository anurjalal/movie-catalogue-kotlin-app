package my.jalal.jetpack.moviecatalogue.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_favorite.*
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.MovieLocal
import my.jalal.jetpack.moviecatalogue.ui.moviedetail.MovieDetailActivity
import my.jalal.jetpack.moviecatalogue.viewmodel.ViewModelFactory

class MovieFavoriteFragment : Fragment(), MovieFavoriteFragmentCallback {

    private lateinit var movieViewModel: MovieFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        val adapter = MovieFavoriteAdapter(this)
        with(rv_movies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            this.adapter = adapter
        }
        val factory = ViewModelFactory.getInstance(requireContext())
        movieViewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]
        movieViewModel.getMovies().observe(viewLifecycleOwner,
            Observer {
                showLoading(true)
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
                showLoading(false)
            })

    }

    override fun onShareClick(movie: MovieLocal) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_DATA, movie)
        startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
        }
    }

}