package com.syntax.mvp.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syntax.mvp.R
import com.syntax.mvp.adapter.FavoritesAdapter
import com.syntax.mvp.db.FavoritesMovies
import com.syntax.mvp.db.database
import com.syntax.mvp.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesFragment : Fragment() {

    private lateinit var favoritesAdapter: FavoritesAdapter
    private var favoritesMovies: MutableList<FavoritesMovies> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        favoritesAdapter = FavoritesAdapter(context, favoritesMovies)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerview_fav.layoutManager = layoutManager
        recyclerview_fav.adapter = favoritesAdapter
        showDataFavorites()
    }

    override fun onResume() {
        showDataFavorites()
        super.onResume()
    }

    private fun showDataFavorites() {
        favoritesMovies.clear()
        context?.database?.use {
            val resultMovies = select(FavoritesMovies.TABLE_MOVIES)
            val favorites = resultMovies.parseList(classParser<FavoritesMovies>())
            favoritesMovies.addAll(favorites)
            favoritesAdapter.notifyDataSetChanged()
        }
    }
}
