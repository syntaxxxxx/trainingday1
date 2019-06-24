package com.syntax.mvp.utama

import com.syntax.mvp.base.BaseView
import com.syntax.mvp.model.ResultsItem

interface MoviesView : BaseView {

    fun showDataMovies(item: List<ResultsItem>)
    fun error(message: String?)
}