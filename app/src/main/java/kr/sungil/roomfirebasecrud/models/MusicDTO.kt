package kr.sungil.roomfirebasecrud.models

import com.google.firebase.database.Exclude

data class MusicDTO(
	val idx: Int = 0,
	val key: String = "",
	var title: String = "",
	var singer: String = ""
) {
	constructor() : this(0, "", "", "")

	@Exclude
	fun toMap(): Map<String, Any> {
		return mapOf(
			"idx" to idx,
			"key" to key,
			"title" to title,
			"singer" to singer
		)
	}
}
