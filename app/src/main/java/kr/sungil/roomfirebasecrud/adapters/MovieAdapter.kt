package kr.sungil.roomfirebasecrud.adapters

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.sungil.roomfirebasecrud.databinding.RvItemBookBinding
import kr.sungil.roomfirebasecrud.models.BookDTO
import kr.sungil.roomfirebasecrud.models.MovieDTO

class MovieAdapter(
    private val onItemClick: (MovieDTO) -> Unit
):ListAdapter<MovieDTO, MovieAdapter.ViewHolder>() {


    inner class ViewHolder(
        private val binding: RvItemBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: MovieDTO) {
            binding.apply {
                tvIdx.text = book.idx.toString()
                tvTitle.text = book.title
                tvAuthor.text = book.director
            }
        }
    }


    //diffUtil
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