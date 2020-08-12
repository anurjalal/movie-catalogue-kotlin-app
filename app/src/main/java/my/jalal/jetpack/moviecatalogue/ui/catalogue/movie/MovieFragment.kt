package my.jalal.jetpack.moviecatalogue.ui.catalogue.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.Movie
import my.jalal.jetpack.moviecatalogue.ui.moviedetail.MovieDetailActivity
import my.jalal.jetpack.moviecatalogue.utils.MovieToMovieLocalAdapter
import my.jalal.jetpack.moviecatalogue.viewmodel.ViewModelFactory
import java.util.*

class MovieFragment : Fragment(), MovieFragmentCallback {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var language: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        val adapter = MovieAdapter(this)
        with(rv_movies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }
        val factory = ViewModelFactory.getInstance(requireContext())
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        language = Locale.getDefault().toLanguageTag()
        movieViewModel.getMovies(language).observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {
                if (it != null) {
                    showLoading(true)
                    adapter.setData(it)
                    Log.d("this", it.toString())
                    showLoading(false)
                }
            })

    }

    override fun onShareClick(movie: Movie) {
        val movieLocal = MovieToMovieLocalAdapter(movie).getMovieLocal()
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_DATA, movieLocal)
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