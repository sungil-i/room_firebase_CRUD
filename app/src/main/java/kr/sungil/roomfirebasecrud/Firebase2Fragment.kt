package kr.sungil.roomfirebasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.sungil.roomfirebasecrud.databinding.FragmentFirebase2Binding

class Firebase2Fragment : Fragment(R.layout.fragment_firebase2) {
	private var binding: FragmentFirebase2Binding? = null
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentFirebase2Binding.bind(view)
		binding = _binding
	}
}