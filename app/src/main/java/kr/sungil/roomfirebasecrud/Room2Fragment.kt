package kr.sungil.roomfirebasecrud

import android.app.appsearch.GlobalSearchSession
import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kr.sungil.roomfirebasecrud.adapters.MovieAdapter
import kr.sungil.roomfirebasecrud.databinding.FragmentRoom2Binding
import kr.sungil.roomfirebasecrud.models.MovieDTO
import kr.sungil.roomfirebasecrud.room.AppDatabase
import kr.sungil.roomfirebasecrud.room.getAppDatabase

class Room2Fragment : Fragment(R.layout.fragment_room2) {
	private var binding: FragmentRoom2Binding? = null
	private lateinit var db: AppDatabase
	private lateinit var adapter: MovieAdapter
	private val movieList = mutableListOf<MovieDTO>()
	private var selectedItem: MovieDTO? = null

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val _binding = FragmentRoom2Binding.bind(view)
		binding = _binding
		db = context?.let { getAppDatabase(it) }!!

		initEditTexts()
		initButtons()
		initRecyclerView()
		readData()
	}

	private fun initEditTexts() {
		binding!!.apply {
			etTitle.addTextChangedListener {
				val enable = etTitle.text.trim().isNotEmpty() && etDirector.text.trim().isNotEmpty()
				btSave.isEnabled = enable
				btCancel.isEnabled = enable
			}
			etDirector.addTextChangedListener {
				val enable = etTitle.text.trim().isNotEmpty() && etDirector.text.trim().isNotEmpty()
				btSave.isEnabled = enable
				btCancel.isEnabled = enable
			}
		}
	}

	private fun initButtons() {
		binding!!.apply {
			btSave.setOnClickListener {
				if (btSave.text.equals("저장하기")) {
					val movie = MovieDTO(
						movieList.size + 1, etTitle.text.toString(), etDirector.text.toString()
					)
					Thread {
						db.movieDao().insertMovie(movie)
					}.start()
					etTitle.text.clear()
					etDirector.text.clear()
					readData()
				} else if (btSave.text.equals("수정하기")) {
					if (selectedItem != null) {
						Thread {
							val updateMovie = MovieDTO(
								selectedItem!!.idx,
								binding!!.etTitle.text.toString(),
								binding!!.etDirector.text.toString()
							)
							db.movieDao().updateMovie(updateMovie)
						}.start()
						etTitle.text.clear()
						etDirector.text.clear()
						readData()
						selectedItem = null
					}
				}
			}
			btCancel.setOnClickListener {
				selectedItem = null
				binding!!.apply {
					etTitle.text.clear()
					etDirector.text.clear()
					btSave.text = "저장하기"
				}
			}
		}
	}

	private fun readData() {
		var movies: List<MovieDTO>
		Thread {
			movieList.clear()
			movies = db.movieDao().getAllMovies()
			for (m in movies) {
				movieList.add(m)
			}
		}.start()
		adapter.notifyDataSetChanged()
	}

	private fun initRecyclerView() {
		binding!!.apply {
			adapter = MovieAdapter(onItemClicked = {
				selectedItem = MovieDTO(
					idx = it.idx,
					title = it.title,
					director = it.director
				)
				etTitle.setText(selectedItem!!.title)
				etDirector.setText(selectedItem!!.director)
				btSave.text = "수정하기"
			}, onItemLongClicked = {
				if (selectedItem != null) {
					Thread {
						db.movieDao().deleteMovie(selectedItem!!)
					}.start()
					btSave.text = "저장하기"
					etTitle.text.clear()
					etDirector.text.clear()
					readData()
					selectedItem = null
				}
				return@MovieAdapter true
			}, deleteItem = {
				if (selectedItem != null) {
					Thread {
						db.movieDao().deleteMovie(selectedItem!!)
					}.start()
					btSave.text = "저장하기"
					etTitle.text.clear()
					etDirector.text.clear()
					readData()
					selectedItem = null
				}
			})
			adapter.submitList(movieList)
			rvMovies.adapter = adapter
		}
	}
}