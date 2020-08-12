package my.jalal.jetpack.moviecatalogue.ui.catalogue.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tv_show.*
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.data.source.remote.entity.TvShow
import my.jalal.jetpack.moviecatalogue.ui.tvshowdetail.TvShowDetailActivity
import my.jalal.jetpack.moviecatalogue.utils.TvToTvLocalAdapter
import my.jalal.jetpack.moviecatalogue.viewmodel.ViewModelFactory
import java.util.*

class TvShowFragment : Fragment(), TvShowFragmentCallback {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var language: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        val adapter = TvShowAdapter(this)
        with(rv_tv_shows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
        language = Locale.getDefault().toLanguageTag()
        viewModel.getTvShows(language).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it != null) {
                showLoading(true)
                adapter.setData(it)
                Log.d("this", it.toString())
                showLoading(false)
            }
        })
    }

    override fun onShareClick(tvShow: TvShow) {
        val intent = Intent(activity, TvShowDetailActivity::class.java)
        val tvShowLocal = TvToTvLocalAdapter(tvShow).getTvLocal()
        intent.putExtra(TvShowDetailActivity.EXTRA_DATA, tvShowLocal)
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