package kr.sungil.roomfirebasecrud.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.sungil.roomfirebasecrud.databinding.RvItemBookBinding
import kr.sungil.roomfirebasecrud.databinding.RvItemMovieBinding
import kr.sungil.roomfirebasecrud.models.MovieDTO

class MovieAdapter(onItemClick: () -> Unit) : ListAdapter<MovieDTO, MovieAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    // inner class
    inner class ViewHolder(
        private val binding: RvItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieDTO) {
            binding.apply {
                tvIdx.text = movie.idx.toString()
                tvTitle.text = movie.title
                tvDirector.text = movie.author
            }
        }
    }

    // diffUtil
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