package com.peter.creditfins.data.api

import com.peter.creditfins.data.model.Movie
import com.peter.creditfins.data.model.Review

interface ApiHelper {

    suspend fun getMovieList(): List<Movie>
    suspend fun getReviewList(movieId: Int): List<Review>
    suspend fun setFav(movieId: Int, fav: Boolean)

}