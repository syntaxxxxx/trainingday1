package com.syntax.mvp.utama


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syntax.mvp.BuildConfig
import com.syntax.mvp.R
import com.syntax.mvp.adapter.MoviesAdapter
import com.syntax.mvp.model.ResultsItem
import kotlinx.android.synthetic.main.fragment_movies.*
import org.jetbrains.anko.toast

/**
 * A simple [Fragment] subclass.
 *
 */
class MoviesFragment: Fragment(), MoviesView {

    private lateinit var presenter: MoviesPresenter
    private var movieList: MutableList<ResultsItem?>? = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = MoviesPresenter()
        presenter.fetchDataUpComing(BuildConfig.API_KEY, BuildConfig.LANGUAGE)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun showDataMovies(item: List<ResultsItem>) {
        movieList?.clear()
        movieList?.addAll(item)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = MoviesAdapter(movieList, context)
    }


    override fun error(message: String?) {
        context?.toast(message!!)
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
