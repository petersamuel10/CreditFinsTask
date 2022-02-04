package com.peter.creditfins.di

import android.content.Context
import com.peter.creditfins.base.App
import com.peter.creditfins.data.api.ApiHelper
import com.peter.creditfins.data.api.ApiHelperImpl
import com.peter.creditfins.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context): App {
        return context as App
    }

    @Named("api_url")
    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/movie/"


    @Provides
    @Singleton
    fun provideRetrofit(@Named("api_url") baseURL: String) =
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}