package kr.sungil.roomfirebasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.sungil.roomfirebasecrud.databinding.FragmentRoom1Binding

class Room1Fragment : Fragment(R.layout.fragment_room1) {
	private var binding: FragmentRoom1Binding? = null
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentRoom1Binding.bind(view)
		binding = _binding
	}
}