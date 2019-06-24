package com.syntax.mvp.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FavoritesMovies(
    val id: Long?,
    val moviesId: String?,
    val moviesName: String?,
    val moviesPop: String?,
    val moviesDate: String?,
    val moviesOverview: String?,
    val urlImages: String?
) : Parcelable {
    companion object {
        const val TABLE_MOVIES: String = "TABLE_MOVIES"
        const val _ID: String = "ID_"
        const val MOVIES_ID: String = "MOVIES_ID"
        const val MOVIES_NAME: String = "MOVIES_NAME"
        const val MOVIES_POP: String = "MOVIES_POP"
        const val MOVIES_DATE: String = "MOVIES_DATE"
        const val MOVIES_OVERVIEW: String = "MOVIES_OVERVIEW"
        const val IMAGES: String = "MOVIES_IMAGES"
    }
}