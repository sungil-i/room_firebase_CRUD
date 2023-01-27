package kr.sungil.roomfirebasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import kr.sungil.roomfirebasecrud.Datas.bookItems
import kr.sungil.roomfirebasecrud.adapters.BookAdapter
import kr.sungil.roomfirebasecrud.databinding.FragmentRoom1Binding
import kr.sungil.roomfirebasecrud.models.BookDTO
import kr.sungil.roomfirebasecrud.room.AppDatabase
import kr.sungil.roomfirebasecrud.room.getAppDatabase

class Room1Fragment : Fragment(R.layout.fragment_room1) {
	private var binding: FragmentRoom1Binding? = null
	private lateinit var db: AppDatabase
	private lateinit var adapter: BookAdapter
	private val bookList = mutableListOf<BookDTO>()

	override fun onResume() {
		super.onResume()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentRoom1Binding.bind(view)
		binding = _binding
		db = context?.let { getAppDatabase(it) }!!

		initDatabase()
		initRecyclerView()
	}

	private fun initDatabase() {
		Thread {
			for (b in bookItems) {
				db.bookDao().insertBook(b)
			}
		}.start()
	}

	private fun initRecyclerView() {
		Thread {
			val books = db.bookDao().getAllBooks()
			for (b in books) {
				bookList.add(b)
			}
		}.start()

		adapter = BookAdapter()
		binding!!.rvBooks.adapter = adapter
		adapter.submitList(bookList)
	}
}