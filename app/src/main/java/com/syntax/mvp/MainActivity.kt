package com.syntax.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.syntax.mvp.favorites.FavoritesFragment
import com.syntax.mvp.utama.MoviesFragment
import kotlinx.android.synthetic.main.bottom_nav.*
import kotlinx.android.synthetic.main.home_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.movies -> {
                    transactionMoviesFragment(savedInstanceState)
                }

                R.id.favorites -> {
                    transactionFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.movies
    }

    private fun transactionMoviesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, MoviesFragment(), MoviesFragment::class.java.simpleName)
                .commit()
        }
    }


    private fun transactionFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                .commit()
        }
    }
}