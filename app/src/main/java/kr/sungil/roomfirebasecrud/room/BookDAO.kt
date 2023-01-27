package kr.sungil.roomfirebasecrud.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.sungil.roomfirebasecrud.models.BookDTO

@Dao
interface BookDAO {
	@Query("SELECT * FROM book_table")
	fun getAllBooks(): List<BookDTO>

	@Query("SELECT * FROM book_table WHERE title== :title")
	fun getBookByTitle(title: String): BookDTO

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertBook(book: BookDTO)
}