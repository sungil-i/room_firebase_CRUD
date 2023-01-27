package kr.sungil.roomfirebasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import kr.sungil.roomfirebasecrud.Datas.bookItems
import kr.sungil.roomfirebasecrud.adapters.BookAdapter
import kr.sungil.roomfirebasecrud.adapters.MovieAdapter
import kr.sungil.roomfirebasecrud.databinding.FragmentRoom1Binding
import kr.sungil.roomfirebasecrud.databinding.FragmentRoom2Binding
import kr.sungil.roomfirebasecrud.models.BookDTO
import kr.sungil.roomfirebasecrud.models.MovieDTO
import kr.sungil.roomfirebasecrud.room.AppDatabase
import kr.sungil.roomfirebasecrud.room.getAppDatabase

class Room2Fragment : Fragment(R.layout.fragment_room1) {
	private var binding: FragmentRoom2Binding? = null
	private lateinit var db: AppDatabase
	private lateinit var adapter: MovieAdapter
	private val movieList = mutableListOf<MovieDTO>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentRoom2Binding.bind(view)
		binding = _binding
		db = context?.let { getAppDatabase(it) }!!

		initDatabase()
		initRecyclerView()
	}

	private fun initDatabase() {
		Thread {
			for (m in movieList) {
				db.movieDao().insertMovie(m)
			}
		}.start()
	}

	private fun initRecyclerView() {
		Thread {
			val movies = db.movieDao().getAllMovies()
			for (m in movies) {
				movieList.add(m)
			}
		}.start()

		adapter = MovieAdapter()
		binding!!.rvMovies.adapter = adapter
		adapter.submitList(movieList)
	}
}