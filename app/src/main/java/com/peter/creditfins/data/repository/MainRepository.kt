package com.peter.creditfins.data.repository

import com.peter.creditfins.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getMovieList() = apiHelper.getMovieList()
}