package kr.sungil.roomfirebasecrud.room

import androidx.room.*

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movie_table")
    fun getAllMovies() : List<MovieDAO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie : MovieDAO)

    @Update
    fun updateMovie(movie : MovieDAO)

    @Delete
    fun deleteMovie(movie : MovieDAO)

}