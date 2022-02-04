package com.peter.creditfins.data.api

import com.peter.creditfins.data.model.MoviesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("popular?api_key=cb9372c57f8bb6113a22276f4d92bf40")
    suspend fun getMovieList(): MoviesResponse

}