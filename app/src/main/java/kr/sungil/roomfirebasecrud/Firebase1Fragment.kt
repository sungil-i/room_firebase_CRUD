package kr.sungil.roomfirebasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.ChildEvent
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.sungil.roomfirebasecrud.Datas.bookItems
import kr.sungil.roomfirebasecrud.adapters.BookAdapter
import kr.sungil.roomfirebasecrud.databinding.FragmentFirebase1Binding
import kr.sungil.roomfirebasecrud.models.BookDTO

class Firebase1Fragment : Fragment(R.layout.fragment_firebase1) {
	private var binding: FragmentFirebase1Binding? = null
	private val bookDB: DatabaseReference by lazy { Firebase.database.reference.child("BOOKS") }
	private lateinit var adapter: BookAdapter
	private val bookList = mutableListOf<BookDTO>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentFirebase1Binding.bind(view)
		binding = _binding

		initDatabase()
	}

	private fun initDatabase() {
		for (b in bookItems) {
			val bookRef = bookDB.push()
			val bookId = bookRef.key
			bookRef.setValue(b)
		}
		initRecyclerView()
	}

	private fun initRecyclerView() {
		val listener = object : ChildEventListener {
			override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
				val book = snapshot.getValue(BookDTO::class.java)
				book ?: return

				bookList.add(book)
				adapter.submitList(bookList)
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

		bookDB.addChildEventListener(listener)
		bookList.clear()
		adapter = BookAdapter()
		binding!!.rvBooks.adapter = adapter
	}
}