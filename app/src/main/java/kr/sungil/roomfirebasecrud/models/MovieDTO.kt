package kr.sungil.roomfirebasecrud.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieDTO(
	@PrimaryKey val idx: Int,
	@ColumnInfo(name = "title") val title: String, // 영화 제목
	@ColumnInfo(name = "director") val author: String // 감독
) {
	constructor() : this(0, "", "")
}