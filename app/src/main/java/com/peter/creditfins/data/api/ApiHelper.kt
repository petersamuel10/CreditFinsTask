package com.peter.creditfins.data.api

import com.peter.creditfins.data.model.Movie

interface ApiHelper {

    suspend fun getMovieList(): List<Movie>
    suspend fun setFav(movieId:Int, fav: Boolean)

}