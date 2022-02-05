package com.peter.creditfins.data.api

import com.peter.creditfins.data.model.MoviesResponse
import com.peter.creditfins.data.model.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("popular?api_key=cb9372c57f8bb6113a22276f4d92bf40")
    suspend fun getMovieList(): MoviesResponse

    @GET("{movie_id}/reviews?api_key=cb9372c57f8bb6113a22276f4d92bf40")
    suspend fun getReviewList(@Path("movie_id") movieId:Int): ReviewResponse

}