package com.peter.creditfins.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int,
    @SerializedName("results")
    val movieList: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)