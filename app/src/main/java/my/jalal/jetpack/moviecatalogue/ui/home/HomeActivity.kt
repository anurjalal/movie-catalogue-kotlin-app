package my.jalal.jetpack.moviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.*
import my.jalal.jetpack.moviecatalogue.R
import my.jalal.jetpack.moviecatalogue.ui.catalogue.CatalogueFragment
import my.jalal.jetpack.moviecatalogue.ui.favorite.FavoriteFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadFragment(CatalogueFragment())
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.btn_catalogue -> {
                    loadFragment(CatalogueFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.btn_favorite -> {
                    loadFragment(FavoriteFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}