package kr.sungil.roomfirebasecrud.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kr.sungil.roomfirebasecrud.models.BookDTO

@Database(entities = [BookDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun bookDao(): BookDAO
	abstract fun movieDao(): BookDAO
}

fun getAppDatabase(context: Context): AppDatabase {
	return Room.databaseBuilder(
		context,
		AppDatabase::class.java,
		"SampleDB"
	).build()
}