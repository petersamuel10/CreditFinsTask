package com.peter.creditfins.di

import androidx.room.Room
import com.peter.creditfins.base.App
import com.peter.creditfins.data.cache.AppDatabase
import com.peter.creditfins.data.cache.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: App): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideQueueDao(db: AppDatabase): MovieDao {
        return db.movieDao()
    }

}