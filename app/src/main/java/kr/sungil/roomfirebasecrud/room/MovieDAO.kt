package kr.sungil.roomfirebasecrud.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.sungil.roomfirebasecrud.models.MovieDTO

@Dao
interface MovieDAO {
	@Query("SELECT * FROM movie_table")
	fun getAllMovies(): List<MovieDTO>

	@Query("SELECT * FROM movie_table WHERE title == :title")
	fun getMoviesByTitle(title: String): MovieDTO

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertMovie(movie: MovieDTO)
}