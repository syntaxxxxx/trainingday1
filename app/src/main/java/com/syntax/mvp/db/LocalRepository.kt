package com.syntax.mvp.db

interface LocalRepository {

    fun likeMovies(
        moviesId: String,
        moviesName: String,
        moviesPop: String,
        moviesDate: String,
        moviesOverview: String,
        moviesImages: String
    )

    fun unLikeMovies(moviesId: String)

}