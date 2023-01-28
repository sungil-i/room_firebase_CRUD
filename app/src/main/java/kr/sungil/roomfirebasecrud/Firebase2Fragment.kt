package kr.sungil.roomfirebasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.sungil.roomfirebasecrud.adapters.MusicAdapter
import kr.sungil.roomfirebasecrud.databinding.FragmentFirebase2Binding
import kr.sungil.roomfirebasecrud.models.MusicDTO

class Firebase2Fragment : Fragment(R.layout.fragment_firebase2) {
	private var binding: FragmentFirebase2Binding? = null
	private val musicDB: DatabaseReference by lazy { Firebase.database.reference.child("MUSICS") }
	private lateinit var adapter: MusicAdapter
	private val musicList = mutableListOf<MusicDTO>()
	private lateinit var listener: Any
	private var selectedItem: MusicDTO? = null

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
				if (btSave.text.equals(getString(R.string.bt_save))) {
					insertIntoFirebase()
				} else if (btSave.text.equals(getString(R.string.bt_modify))) {
					updateIntoFirebase()
				}
			}
			btCancel.setOnClickListener {
				resetItemSelected()
			}
		}
	}

	private fun insertIntoFirebase() {
		binding!!.apply {
			val dbRef = musicDB.push()
			val dbKey = dbRef.key!!
			val insertMusic = MusicDTO(
				adapter.itemCount + 1,
				dbKey,
				etTitle.text.toString(),
				etSinger.text.toString()
			)
			dbRef.setValue(insertMusic)
			Toast.makeText(
				context, "저장 성공!", Toast.LENGTH_LONG
			).show()
			etTitle.text.clear()
			etSinger.text.clear()
		}
	}

	private fun updateIntoFirebase() {
		if (selectedItem != null) {
			binding!!.apply {
				val updateMusic = MusicDTO(
					idx = selectedItem!!.idx,
					key = selectedItem!!.key,
					title = etTitle.text.toString(),
					singer = etSinger.text.toString()
				)
				musicDB.child(selectedItem!!.key)
					.updateChildren(updateMusic.toMap())
				Toast.makeText(
					context, "수정 성공!", Toast.LENGTH_LONG
				).show()
			}
		}
	}

	private fun deleteFromFirebase() {
		if (selectedItem != null) {
			binding!!.apply {
				musicDB.child(selectedItem!!.key).removeValue()
				resetItemSelected()
				Toast.makeText(
					context, "삭제 완료!", Toast.LENGTH_LONG
				).show()
			}
		}
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
					val music = snapshot.getValue(MusicDTO::class.java)
					music ?: return

					for (m in musicList) {
						if (m.idx == music.idx) {
							m.title = music.title
							m.singer = music.singer
						}
					}
					adapter.submitList(musicList)
					adapter.notifyDataSetChanged()
				}

				override fun onChildRemoved(snapshot: DataSnapshot) {
					val music = snapshot.getValue(MusicDTO::class.java)
					music ?: return

					var delMusic: MusicDTO? = null
					for (m in musicList) {
						if (m.idx == music.idx) {
							delMusic = m
						}
					}
					if (delMusic != null) {
						musicList.remove(delMusic)
					}
					adapter.submitList(musicList)
					adapter.notifyDataSetChanged()
				}

				override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
				}

				override fun onCancelled(error: DatabaseError) {
				}
			}
			musicDB.addChildEventListener(listener as ChildEventListener)
			musicList.clear()
			adapter = MusicAdapter(onItemClicked = { music ->
				setItemSelected(music)
			}, deleteItem = {
				deleteFromFirebase()
			})
			binding!!.rvMusic.adapter = adapter
		}
	}

	private fun setItemSelected(music: MusicDTO) {
		selectedItem = music
		binding!!.apply {
			etTitle.setText(music.title)
			etSinger.setText(music.singer)
			btSave.text = getString(R.string.bt_modify)
		}
	}

	private fun resetItemSelected() {
		selectedItem = null
		binding!!.apply {
			etTitle.text.clear()
			etSinger.text.clear()
			btSave.text = getString(R.string.bt_save)
		}
	}
}