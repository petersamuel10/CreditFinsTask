package com.peter.creditfins.data.repository

import com.peter.creditfins.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getMovieList() = apiHelper.getMovieList()
    suspend fun getReviewList(movieId:Int) = apiHelper.getReviewList(movieId)
    suspend fun setFav(movieId: Int, fav: Boolean) = apiHelper.setFav(movieId, fav)
}