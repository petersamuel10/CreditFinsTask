package com.peter.creditfins.data.api

import com.peter.creditfins.data.model.Movies

interface ApiHelper {

    suspend fun getMovieList(): Movies

}