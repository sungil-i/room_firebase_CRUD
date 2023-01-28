package kr.sungil.roomfirebasecrud.models

data class MusicDTO(
	val idx: Int = 0,
	var title: String = "",
	var singer: String = ""
) {
	constructor() : this(0, "", "")
}
