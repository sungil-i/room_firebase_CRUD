package kr.sungil.roomfirebasecrud.room

import androidx.room.*
import kr.sungil.roomfirebasecrud.models.MovieDTO

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): List<MovieDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieDTO)

    @Update
    fun updateMovie(movie: MovieDTO)

    @Delete
    fun deleteMovie(movie: MovieDTO)
}