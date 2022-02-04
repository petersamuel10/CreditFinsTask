package com.peter.creditfins.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peter.creditfins.data.model.Movie

@Database(
    entities = [Movie::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        val DATABASE_NAME: String = "creditFins_db"
    }
}