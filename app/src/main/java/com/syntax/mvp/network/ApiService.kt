package com.syntax.mvp.network

import com.syntax.mvp.model.ResponseMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<ResponseMovies>
}