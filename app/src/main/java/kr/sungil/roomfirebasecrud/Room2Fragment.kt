package kr.sungil.roomfirebasecrud

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
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

        initEditText()
        initButton()
//        initDatabase()
        initRecyclerView()
    }

    private fun initButton() {
        binding!!.apply {
            btMvSave.isEnabled = false;
            btMvCancel.isEnabled = false;
            btMvSave.setOnClickListener {
                val movie = MovieDTO(
                    adapter.itemCount + 1,
                    etMvTitle.text.toString(),
                    etMvDirector.text.toString()
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

    private fun initEditText() {
        binding!!.apply {
            etMvTitle.addTextChangedListener {
                val isOk = etMvTitle.text.isNotEmpty() && etMvDirector.text.isNotEmpty()
                btMvSave.isEnabled = isOk
                btMvCancel.isEnabled = isOk
            }
            etMvDirector.addTextChangedListener {
                val isOk = etMvTitle.text.isNotEmpty() && etMvDirector.text.isNotEmpty()
                btMvSave.isEnabled = isOk
                btMvCancel.isEnabled = isOk
            }
        }
    }

    private fun initDatabase() {
        Thread {
            for (m in movieItems) {
                db.movieDao().insertMovie(m)
            }
        }.start()
    }

    private fun initRecyclerView() {
        binding!!.apply {
            adapter = MovieAdapter(onItemClick = {})
            rvMovies.adapter = adapter
            adapter.submitList(movieList)
        }
    }
}