package com.syntax.mvp.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.syntax.mvp.BuildConfig
import com.syntax.mvp.R
import com.syntax.mvp.db.FavoritesMovies
import com.syntax.mvp.db.database
import com.syntax.mvp.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailView {

    companion object {
        const val ID: String = "ID"
        const val TITLE: String = "TITLE"
        const val POP: String = "POP"
        const val DATE: String = "DATE"
        const val OVERVIEW: String = "OVERVIEW"
        const val IMAGES: String = "IMAGES"
    }

    private lateinit var id: String
    private lateinit var title: String
    private lateinit var pop: String
    private lateinit var date: String
    private lateinit var overview: String
    private lateinit var images: String

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter = DetailPresenter(this)

        id = intent.getStringExtra(ID)
        title = intent.getStringExtra(TITLE)
        pop = intent.getStringExtra(POP)
        date = intent.getStringExtra(DATE)
        overview = intent.getStringExtra(OVERVIEW)
        images = intent.getStringExtra(IMAGES)

        tv_title_detail.text = title
        tv_popularity_detail.text = pop
        tv_release_detail.text = date
        tv_overview.text = overview

        Picasso.get()
            .load(BuildConfig.IMAGES + images)
            .resize(120, 160)
            .centerCrop()
            .into(iv_poster_detail)

        favState()

    }

    private fun favState() {
        database.use {
            val result = select(FavoritesMovies.TABLE_MOVIES)
                .whereArgs(
                    "(MOVIES_ID = {moviesId})",
                    "moviesId" to id
                )
            val fav = result.parseList(classParser<FavoritesMovies>())
            fav
            if (!fav.isEmpty()) isFavorite = true
        }
    }

    override fun displayDataMovies(item: List<ResultsItem>) {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movies_menu, menu)
        menuItem = menu
        setLike()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.fav -> {
                if (isFavorite) {
                    presenter.unLikeMovies(id)
                    toast(getString(R.string.delete_success))
                    isFavorite = !isFavorite

                } else {
                    presenter.likeMovies(
                        id, title, pop, date, overview, images
                    )
                    toast(getString(R.string.success))
                    isFavorite = !isFavorite
                }
                setLike()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun setLike() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun error(message: String?) {
        toast(message!!)
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }
}