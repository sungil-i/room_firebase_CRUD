package kr.sungil.roomfirebasecrud.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity(tableName = "movie_table")
data class MovieDTO(
    @PrimaryKey val idx: Int,
    @ColumnInfo(name = "title") val title: String, // 책제목
    @ColumnInfo(name = "author") val author: String // 지은이
) {
    constructor() : this(0, "", "")
}