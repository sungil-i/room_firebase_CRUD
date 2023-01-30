package kr.sungil.roomfirebasecrud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kr.sungil.roomfirebasecrud.adapters.MovieAdapter
import kr.sungil.roomfirebasecrud.databinding.FragmentRoom2Binding
import kr.sungil.roomfirebasecrud.models.MovieDTO
import kr.sungil.roomfirebasecrud.room.AppDatabase
import kr.sungil.roomfirebasecrud.room.MovieDAO

class Room2Fragment : Fragment(R.layout.fragment_room2) {
	private var binding: FragmentRoom2Binding? = null
	private lateinit var db: AppDatabase
	private lateinit var adapter: MovieAdapter
	private val movieList = mutableListOf<MovieDTO>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentRoom2Binding.bind(view)
		binding = _binding

		initEditText()
		initButton()
		initRecyclerView()

		initEditText()
		binding!!.apply {
			etTitle.addTextChangedListener {
				val isOk = etTitle.text.isNotEmpty() && etDirector.text.isNullOrEmpty()
				btSave.isEnabled = isOk
				btCancel.isEnabled = isOk
			}
			etDirector.addTextChangedListener {
				val isOk = etTitle.text.isNotEmpty() && etDirector.text.isNullOrEmpty()
				btSave.isEnabled = isOk
				btCancel.isEnabled = isOk
			}
		}
	}

	private fun initRecyclerView() {
		binding!!.apply {
			adapter = MovieAdapter(onItemClick = {})
			rvMovies.adapter =  adapter
			adapter.submitList(movieList)
		}
	}

	private fun initButton() {
		binding!!.apply {
			btSave.isEnabled = false
			btCancel.isEnabled = false
			btSave.setOnClickListener {
				val movie = MovieDTO(
					adapter.itemCount + 1,
					etTitle.text.toString(),
					etDirector.text.toString()
				)
				db.movieDao().insertMovie(movie)
				readData()
			}
		}
	}

	private fun readData() {
		val movies = db.movieDao().getAllMovies()
		if (movies.size > 0) {
			for (m in movies) {
				movieList.add(m)
			}
			adapter.notifyDataSetChanged()
		}
	}

	private fun initEditText() {
		binding!!.apply {
			btSave.isEnabled = false
			btCancel.isEnabled = false
		}
	}
}