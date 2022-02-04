package com.peter.creditfins.data.api

import com.peter.creditfins.data.cache.MovieDao
import com.peter.creditfins.data.model.Movie
import com.peter.creditfins.util.NetworkHelper
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : ApiHelper {

    override suspend fun getMovieList(): List<Movie> {

        return if (networkHelper.isNetworkConnected()) {
            val result = apiService.getMovieList()
            if (result.total_results > 0)
                movieDao.insertAllMovies(result.movieList)
            result.movieList
        } else
            movieDao.getAllMovies()
    }

    override suspend fun setFav(movieId: Int, fav: Boolean) {
        movieDao.setFav(movieId, fav)
    }

}