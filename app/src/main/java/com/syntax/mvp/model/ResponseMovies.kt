package com.syntax.mvp.model

import com.google.gson.annotations.SerializedName

data class ResponseMovies(

    @field:SerializedName("results")
    var results: List<ResultsItem>
)