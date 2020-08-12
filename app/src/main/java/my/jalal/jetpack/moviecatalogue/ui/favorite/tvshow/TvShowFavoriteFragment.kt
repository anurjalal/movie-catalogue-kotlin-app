package my.jalal.jetpack.moviecatalogue.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tv_show_favorite.*
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.data.source.local.entity.TvShowLocal
import my.jalal.jetpack.moviecatalogue.ui.tvshowdetail.TvShowDetailActivity
import my.jalal.jetpack.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFavoriteFragment : Fragment(), TvShowFavoriteFragmentCallback {

    private lateinit var viewModel: TvShowFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        val adapter = TvShowFavoriteAdapter(this)
        with(rv_tv_shows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            this.adapter = adapter
        }
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]
        viewModel.getTvShows().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it != null) {
                showLoading(true)
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
                showLoading(false)
            }
        })
    }

    override fun onShareClick(tvShow: TvShowLocal) {
        val intent = Intent(activity, TvShowDetailActivity::class.java)
        intent.putExtra(TvShowDetailActivity.EXTRA_DATA, tvShow)
        startActivity(intent)
    }

    private fun showLoading(state: Boolean)  {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
        }
    }
}