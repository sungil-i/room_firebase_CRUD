package kr.sungil.roomfirebasecrud.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieDTO(
	@PrimaryKey val idx: Int,
	@ColumnInfo(name = "title") var title: String,
	@ColumnInfo(name = "director") var director: String
) {
	constructor() : this(0, "", "")
}