package com.syntax.mvp.base

interface BasePresenter<in T : BaseView> {
    fun onAttach(view: T)
    fun onDettach()
}