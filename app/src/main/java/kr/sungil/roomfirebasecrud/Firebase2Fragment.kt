package kr.sungil.roomfirebasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.sungil.roomfirebasecrud.adapters.BookAdapter
import kr.sungil.roomfirebasecrud.adapters.MusicAdapter
import kr.sungil.roomfirebasecrud.databinding.FragmentFirebase2Binding
import kr.sungil.roomfirebasecrud.models.MusicDTO

class Firebase2Fragment : Fragment(R.layout.fragment_firebase2) {
	private var binding: FragmentFirebase2Binding? = null
	private val musicDB: DatabaseReference by lazy { Firebase.database.reference.child("MUSICS") }
	private lateinit var adapter: MusicAdapter
	private val musicList = mutableListOf<MusicDTO>()
	private lateinit var listener: Any

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentFirebase2Binding.bind(view)
		binding = _binding

		initEditTexts()
		initButtons()
		initRecyclerView()
	}

	private fun initEditTexts() {
		binding!!.apply {
			etTitle.addTextChangedListener {
				val enable = etTitle.text.trim().isNotEmpty() && etSinger.text.trim().isNotEmpty()
				btSave.isEnabled = enable
				btCancel.isEnabled = enable
			}
			etSinger.addTextChangedListener {
				val enable = etTitle.text.trim().isNotEmpty() && etSinger.text.trim().isNotEmpty()
				btSave.isEnabled = enable
				btCancel.isEnabled = enable
			}
		}
	}

	private fun initButtons() {
		binding!!.apply {
			btSave.setOnClickListener {
				if () {
					val music = MusicDTO(
						adapter.itemCount + 1,
						etTitle.text.toString(),
						etSinger.text.toString()
					)
					insertIntoFirebase(music)
				}
			}
			btCancel.setOnClickListener { }
		}
	}

	private fun insertIntoFirebase(music: MusicDTO) {

	}

	private fun initRecyclerView() {
		binding!!.apply {
			listener = object : ChildEventListener {
				override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
					val music = snapshot.getValue(MusicDTO::class.java)
					music ?: return

					musicList.add(music)
					adapter.submitList(musicList)
					adapter.notifyDataSetChanged()
				}

				override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
				}

				override fun onChildRemoved(snapshot: DataSnapshot) {
				}

				override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
				}

				override fun onCancelled(error: DatabaseError) {
				}
			}
			musicDB.addChildEventListener(listener as ChildEventListener)
			musicList.clear()
			adapter = MusicAdapter()
			binding!!.rvMusic.adapter = adapter
		}
	}
}