package com.syntax.mvp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.syntax.mvp.BuildConfig
import com.syntax.mvp.R
import com.syntax.mvp.detail.DetailActivity
import com.syntax.mvp.model.ResultsItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movies.view.*
import org.jetbrains.anko.startActivity

class MoviesAdapter(
    private val dataMovies: List<ResultsItem?>?,
    private val ctx: Context?
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MoviesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_movies, p0, false))
    }

    override fun getItemCount(): Int = dataMovies?.size!!

    override fun onBindViewHolder(p0: MoviesAdapter.ViewHolder, p1: Int) {
        p0.bindItem(dataMovies?.get(p1)!!)
    }

    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!)
        , LayoutContainer {

        fun bindItem(dataMovies: ResultsItem) {
            itemView.tv_title.text = dataMovies.originalTitle
            itemView.tv_popularity.text = dataMovies.popularity.toString()
            itemView.tv_release.text = dataMovies.releaseDate

            Picasso.get()
                .load(BuildConfig.IMAGES + dataMovies.posterPath)
                .resize(120, 160)
                .centerCrop()
                .into(itemView.iv_poster)

            containerView?.setOnClickListener {
                it.context.startActivity<DetailActivity>(
                    DetailActivity.ID to dataMovies.id.toString(),
                    DetailActivity.TITLE to dataMovies.originalTitle!!,
                    DetailActivity.POP to dataMovies.popularity.toString(),
                    DetailActivity.DATE to dataMovies.releaseDate!!,
                    DetailActivity.OVERVIEW to dataMovies.overview!!,
                    DetailActivity.IMAGES to dataMovies.posterPath!!
                )
            }
        }
    }
}