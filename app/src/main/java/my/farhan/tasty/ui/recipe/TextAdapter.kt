package my.farhan.tasty.ui.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.farhan.tasty.databinding.ItemListBinding

class TextAdapter :
    ListAdapter<String, TextAdapter.VH>(Companion) {

    companion object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH.from(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.bv.pos = "${position+1}."
        holder.bv.item = item
    }

    class VH private constructor(val bv: ItemListBinding) :
        RecyclerView.ViewHolder(bv.root) {
        companion object {
            fun from(parent: ViewGroup): VH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val bv = ItemListBinding.inflate(layoutInflater, parent, false)
                return VH(bv)
            }
        }
    }
}