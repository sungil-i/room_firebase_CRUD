package kr.sungil.roomfirebasecrud

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import kr.sungil.roomfirebasecrud.DatasMovie.movieItems
import kr.sungil.roomfirebasecrud.adapters.MovieAdapter
import kr.sungil.roomfirebasecrud.databinding.FragmentRoom2Binding
import kr.sungil.roomfirebasecrud.models.MovieDTO
import kr.sungil.roomfirebasecrud.room.AppMovieDB
import kr.sungil.roomfirebasecrud.room.getAppMovieDB

class Room2Fragment : Fragment(R.layout.fragment_room2) {
	private var binding: FragmentRoom2Binding? = null
	private lateinit var db: AppMovieDB
	private lateinit var adapter: MovieAdapter
	private val movieList = mutableListOf<MovieDTO>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentRoom2Binding.bind(view)
		binding = _binding
		db = context?.let { getAppMovieDB(it) }!!

		initDatabase()
		initRecyclerView()
	}

	private fun initDatabase() {
		Thread {
			for (m in movieItems) {
				db.movieDao().insertMovie(m)
			}
		}.start()
	}

	private fun initRecyclerView() {
		Thread {
			val movies = db.movieDao().getAllMovies()
			for (m in movies) {
				Log.e("test1", "작동하는데 왜안나오지")
				movieList.add(m)
			}
		}.start()

		adapter = MovieAdapter()
		binding!!.rvMovies.adapter = adapter
		adapter.submitList(movieList)
	}
}