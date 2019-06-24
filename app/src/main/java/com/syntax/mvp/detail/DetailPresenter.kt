package com.syntax.mvp.detail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.syntax.mvp.base.BasePresenter
import com.syntax.mvp.db.FavoritesMovies
import com.syntax.mvp.db.LocalRepository
import com.syntax.mvp.db.database
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

class DetailPresenter(var context: Context) : BasePresenter<DetailView>,
    LocalRepository {

    var mainView: DetailView? = null

    override fun likeMovies(
        moviesId: String,
        moviesName: String,
        moviesPop: String,
        moviesDate: String,
        moviesOverview: String,
        moviesImages: String
    ) {
        try {
            context.database.use {
                insert(
                    FavoritesMovies.TABLE_MOVIES,
                    FavoritesMovies.MOVIES_ID to moviesId,
                    FavoritesMovies.MOVIES_NAME to moviesName,
                    FavoritesMovies.MOVIES_POP to moviesPop,
                    FavoritesMovies.MOVIES_DATE to moviesDate,
                    FavoritesMovies.MOVIES_OVERVIEW to moviesOverview,
                    FavoritesMovies.IMAGES to moviesImages
                )
            }

        } catch (e: SQLiteConstraintException) {
            error(e.localizedMessage)
        }
    }

    override fun unLikeMovies(moviesId: String) {
        try {
            context.database.use {
                delete(
                    FavoritesMovies.TABLE_MOVIES, "(MOVIES_ID = {moviesId})",
                    "moviesId" to moviesId
                )
            }

        } catch (e: SQLiteConstraintException) {
            error(e.localizedMessage)
        }
    }

    override fun onAttach(view: DetailView) {
        mainView = view
    }

    override fun onDettach() {
        mainView = null
    }
}