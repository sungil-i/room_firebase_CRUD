package kr.sungil.roomfirebasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.sungil.roomfirebasecrud.databinding.FragmentFirebase1Binding

class Firebase1Fragment : Fragment(R.layout.fragment_firebase1) {
	private var binding: FragmentFirebase1Binding? = null
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentFirebase1Binding.bind(view)
		binding = _binding
	}
}