package com.syntax.mvp.detail

import com.syntax.mvp.base.BaseView
import com.syntax.mvp.model.ResultsItem

interface DetailView : BaseView {

    //    fun checkFavState(favList: List<FavoritesMovies>)
    fun displayDataMovies(item: List<ResultsItem>)

    fun error(message: String?)
}