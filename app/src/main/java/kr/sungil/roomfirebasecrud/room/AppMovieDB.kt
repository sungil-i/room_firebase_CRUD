package kr.sungil.roomfirebasecrud.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kr.sungil.roomfirebasecrud.models.MovieDTO

@Database(entities = [MovieDTO::class], version = 1)
abstract class AppMovieDB : RoomDatabase() {
	abstract fun movieDao(): MovieDAO
}

fun getAppMovieDB(context: Context): AppMovieDB {
	return Room.databaseBuilder(
		context,
		AppMovieDB::class.java,
		"SampleMvDB"
	).fallbackToDestructiveMigration().build()
}