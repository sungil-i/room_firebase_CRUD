package kr.sungil.roomfirebasecrud

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val _binding = FragmentRoom2Binding.bind(view)
        binding = _binding

        // Room Database 초기화
        db = context?.let { getAppDatabase(it) }!!

        initEditText()
        initButton()
        initRecyclerView()
        readData()
    }

    private fun initEditText() {
        binding!!.apply {
            etTitle.addTextChangedListener {
                val isOk = etTitle.text.isNotEmpty() && etDirector.text.isNotEmpty()
                btSave.isEnabled = isOk
                btCancel.isEnabled = isOk
            }
            etDirector.addTextChangedListener {
                val isOk = etTitle.text.isNotEmpty() && etDirector.text.isNotEmpty()
                btSave.isEnabled = isOk
                btCancel.isEnabled = isOk
            }
        }
    }

    private fun initButton() {
        binding!!.apply {
            btSave.isEnabled = false
            btCancel.isEnabled = false
            btSave.setOnClickListener {
                val movie = MovieDTO(
                    movieList.size + 1,
                    etTitle.text.toString(),
                    etDirector.text.toString()
                )
                Thread {
                    db.movieDao().insertMovie(movie)
                }.start()
                readData()
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
            adapter = MovieAdapter(onItemClick = {})
            adapter.submitList(movieList)
            rvMovies.adapter
        }
    }
}