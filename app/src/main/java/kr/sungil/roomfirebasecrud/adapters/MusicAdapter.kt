package kr.sungil.roomfirebasecrud.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.sungil.roomfirebasecrud.databinding.RvItemMusicBinding
import kr.sungil.roomfirebasecrud.models.MusicDTO

class MusicAdapter(
	private val onItemClicked: (MusicDTO) -> Unit,
	private val deleteItem: (MusicDTO) -> Unit
) : ListAdapter<MusicDTO, MusicAdapter.ViewHolder>(diffUtil) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			RvItemMusicBinding.inflate(
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
		private val binding: RvItemMusicBinding
	) : RecyclerView.ViewHolder(binding.root) {
		fun bind(music: MusicDTO) {
			binding.apply {
				tvIdx.text = music.idx.toString()
				tvTitle.text = music.title
				tvSinger.text = music.singer
				ivDelete.setOnClickListener { deleteItem(music) }
				root.setOnClickListener { onItemClicked(music) }
			}
		}
	}

	// diffUtil
	companion object {
		val diffUtil = object : DiffUtil.ItemCallback<MusicDTO>() {
			override fun areItemsTheSame(oldItem: MusicDTO, newItem: MusicDTO): Boolean {
				return oldItem.idx == newItem.idx
			}

			override fun areContentsTheSame(oldItem: MusicDTO, newItem: MusicDTO): Boolean {
				return oldItem == newItem
			}
		}
	}
}