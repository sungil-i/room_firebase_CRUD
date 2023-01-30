package kr.sungil.roomfirebasecrud.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.sungil.roomfirebasecrud.adapters.BookAdapter.Companion.diffUtil
import kr.sungil.roomfirebasecrud.databinding.RvItemBookBinding
import kr.sungil.roomfirebasecrud.databinding.RvItemMovieBinding
import kr.sungil.roomfirebasecrud.models.BookDTO
import kr.sungil.roomfirebasecrud.models.MovieDTO

class MovieAdapter : ListAdapter<MovieDTO, MovieAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(
            RvItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(
        private val binding: RvItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(moive: MovieDTO) {
            binding.apply {
                tvIdx.text = moive.idx.toString()
                tvTitle.text = moive.title
                tvDirector.text = moive.director
            }
        }
    }

    companion object {
        // RecyclerView 의 성능을 높여줍니다.
        val diffUtil = object : DiffUtil.ItemCallback<MovieDTO>() {
            override fun areItemsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
                return oldItem.idx == newItem.idx
            }

            override fun areContentsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
                return oldItem == newItem
            }
        }
    }
}