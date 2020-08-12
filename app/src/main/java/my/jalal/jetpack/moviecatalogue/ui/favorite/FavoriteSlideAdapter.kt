package my.jalal.jetpack.moviecatalogue.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import my.jalal.jetpack.moviecatalogue.ui.favorite.movie.MovieFavoriteFragment
import my.jalal.jetpack.moviecatalogue.ui.favorite.tvshow.TvShowFavoriteFragment


class FavoriteSlideAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            MovieFavoriteFragment()
        } else {
            TvShowFavoriteFragment()
        }
    }
}