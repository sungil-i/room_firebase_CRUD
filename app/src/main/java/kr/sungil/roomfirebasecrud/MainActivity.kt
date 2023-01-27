package kr.sungil.roomfirebasecrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
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

		// 기본 Fragment 설정
		replaceFragment(room1Fragment)
	}

	private fun replaceFragment(fragment: Fragment) {
		// 메뉴 Fragment 를 변경하는 함수
		supportFragmentManager.beginTransaction()
			.replace(R.id.fl_container, fragment)
			.commit()
	}
}