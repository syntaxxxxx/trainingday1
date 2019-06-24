package com.syntax.mvp.utama

import android.annotation.SuppressLint
import com.syntax.mvp.base.BasePresenter
import com.syntax.mvp.model.ResponseMovies
import com.syntax.mvp.network.Http
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesPresenter(var mainView: MoviesView? = null) : BasePresenter<MoviesView> {

    @SuppressLint("CheckResult")
    fun fetchDataUpComing(api_key: String, language: String) {
        Http.retrofit.getUpcomingMovies(api_key, language)
            .enqueue(object : Callback<ResponseMovies> {
                override fun onResponse(call: Call<ResponseMovies>, response: Response<ResponseMovies>) {
                    mainView?.showDataMovies(response.body()?.results!!)
                }

                override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                    mainView?.error(t.message)
                }
            })
    }

    override fun onAttach(view: MoviesView) {
        mainView = view
    }

    override fun onDettach() {
        mainView = null
    }
}