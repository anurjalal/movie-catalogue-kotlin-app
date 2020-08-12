package my.jalal.jetpack.moviecatalogue.ui.catalogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_catalogue.*
import my.jalal.jetpack.moviecatalogue.R

class CatalogueFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = CatalogueSlideAdapter(requireActivity())
        view_pager.adapter = pagerAdapter
        TabLayoutMediator(tabs, view_pager) { tab, position ->
            when(position){
                0 -> tab.text = getString(R.string.movie)
                1 -> tab.text = getString(R.string.tvshow)
            }
        }.attach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalogue, container, false)
    }
}
