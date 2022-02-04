package com.peter.creditfins.data.api

import com.peter.creditfins.data.model.Movies
import javax.inject.Inject
import javax.inject.Named

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService,
    @Named("api_key")
    private val apiKey: String
) : ApiHelper {


    override suspend fun getMovieList(): Movies {

        return apiService.getMovieList()
    }

}