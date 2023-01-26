package kr.sungil.roomfirebasecrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.sungil.roomfirebasecrud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// fragment 생성
		val room1Fragment = Room1Fragment()
		val room2Fragment = Room2Fragment()
		val firebase1Fragment = Firebase1Fragment()
		val firebase2Fragment = Firebase2Fragment()
	}
}