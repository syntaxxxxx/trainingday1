package com.syntax.mvp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.syntax.mvp.BuildConfig
import com.syntax.mvp.R
import com.syntax.mvp.db.FavoritesMovies
import com.syntax.mvp.detail.DetailActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movies.view.*
import org.jetbrains.anko.startActivity

@Suppress("UNNECESSARY_SAFE_CALL")
class FavoritesAdapter(
    private val ctx: Context?,
    private val listData: List<FavoritesMovies?>?
) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoritesAdapter.ViewHolder {
        return FavoritesAdapter.ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_movies, p0, false))
    }

    override fun getItemCount(): Int = listData?.size!!

    override fun onBindViewHolder(p0: FavoritesAdapter.ViewHolder, p1: Int) {
        p0.bindItem(listData?.get(p1))
    }

    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!)
        , LayoutContainer {

        fun bindItem(favoriteMovies: FavoritesMovies?) {
            itemView.tv_title.text = favoriteMovies?.moviesName
            itemView.tv_popularity.text = favoriteMovies?.moviesPop
            itemView.tv_release.text = favoriteMovies?.moviesDate

            Picasso.get()
                .load(BuildConfig.IMAGES + favoriteMovies?.urlImages)
                .resize(120, 160)
                .centerCrop()
                .into(itemView.iv_poster)

            containerView?.setOnClickListener {
                it.context.startActivity<DetailActivity>(
                    DetailActivity.ID to favoriteMovies?.moviesId.toString(),
                    DetailActivity.TITLE to favoriteMovies?.moviesName!!,
                    DetailActivity.POP to favoriteMovies?.moviesPop!!,
                    DetailActivity.DATE to favoriteMovies?.moviesDate!!,
                    DetailActivity.OVERVIEW to favoriteMovies?.moviesOverview!!,
                    DetailActivity.IMAGES to favoriteMovies?.urlImages!!
                )
            }
        }
    }
}