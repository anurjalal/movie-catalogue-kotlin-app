package my.jalal.jetpack.moviecatalogue.ui.catalogue

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import my.jalal.jetpack.moviecatalogue.ui.catalogue.movie.MovieFragment
import my.jalal.jetpack.moviecatalogue.ui.catalogue.tvshow.TvShowFragment

class CatalogueSlideAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            MovieFragment()
        } else {
            TvShowFragment()
        }
    }
}