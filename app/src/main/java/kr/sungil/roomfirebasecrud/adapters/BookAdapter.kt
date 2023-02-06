package kr.sungil.roomfirebasecrud.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.sungil.roomfirebasecrud.databinding.RvItemBookBinding
import kr.sungil.roomfirebasecrud.models.BookDTO

class BookAdapter : ListAdapter<BookDTO, BookAdapter.ViewHolder>(diffUtil) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			RvItemBookBinding.inflate(
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
		private val binding: RvItemBookBinding
	) : RecyclerView.ViewHolder(binding.root) {
		fun bind(book: BookDTO) {
			binding.apply {
				tvIdx.text = book.idx.toString()
				tvTitle.text = book.title
				tvAuthor.text = book.author
			}
		}
	}

	// diffUtil
	companion object {
		// RecyclerView 의 성능을 높여줍니다.
		val diffUtil = object : DiffUtil.ItemCallback<BookDTO>() {
			override fun areItemsTheSame(oldItem: BookDTO, newItem: BookDTO): Boolean {
				return oldItem.idx == newItem.idx
			}

			override fun areContentsTheSame(oldItem: BookDTO, newItem: BookDTO): Boolean {
				return oldItem == newItem
			}
		}
	}
}