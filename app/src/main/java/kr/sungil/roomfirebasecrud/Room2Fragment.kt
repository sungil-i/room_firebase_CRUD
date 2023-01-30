package kr.sungil.roomfirebasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kr.sungil.roomfirebasecrud.databinding.FragmentRoom2Binding

class Room2Fragment : Fragment(R.layout.fragment_room2) {
	private var binding: FragmentRoom2Binding? = null
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentRoom2Binding.bind(view)
		binding = _binding

		initEditText()
		initButton()
		initRecyclerView()

		initEditText()
		binding!!.apply {
			etTitle.addTextChangedListener{
				val isOk = etTitle.text.isNotEmpty() && etDirector.text.isNullOrEmpty()
				btSave.isEnabled=isOk
				btCancel.isEnabled =isOk
			}
			etDirector.addTextChangedListener {
				val isOk = etTitle.text.isNotEmpty() && etDirector.text.isNullOrEmpty()
				btSave.isEnabled=isOk
				btCancel.isEnabled =isOk
			}
		}
	}

	private fun initRecyclerView() {
		TODO("Not yet implemented")
	}

	private fun initButton() {
		TODO("Not yet implemented")
	}

	private fun initEditText() {
		binding!!.apply {
			btSave.isEnabled = false
			btCancel.isEnabled = false
		}
	}
}