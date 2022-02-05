package com.peter.creditfins.data.repository

import com.peter.creditfins.data.api.ApiHelper
import com.peter.creditfins.data.model.Review
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getMovieList(page: Int) = apiHelper.getMovieList(page)
    suspend fun getReviewList(movieId: Int, page: Int): List<Review> = apiHelper.getReviewList(movieId, page)
    suspend fun setFav(movieId: Int, fav: Boolean) = apiHelper.setFav(movieId, fav)
}