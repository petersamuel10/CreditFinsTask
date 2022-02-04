package com.peter.creditfins.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peter.creditfins.data.model.Movie

@Dao
interface MovieDao {

    @Query("DELETE FROM Movie")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movieList: List<Movie>)

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<Movie>

    @Query("UPDATE movie SET fav = :fav WHERE id = :movieId")
    suspend fun setFav(movieId: Int, fav: Boolean)


}