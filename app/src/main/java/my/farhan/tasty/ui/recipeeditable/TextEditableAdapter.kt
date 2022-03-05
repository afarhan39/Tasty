package my.farhan.tasty.ui.recipeeditable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.farhan.tasty.databinding.ItemListEditableBinding

class TextEditableAdapter(private val isStep: Boolean, val listener: Listener) :
    ListAdapter<String, TextEditableAdapter.VH>(Companion) {

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
        holder.bv.isStep = isStep

        holder.bv.tilText.setEndIconOnClickListener {
            listener.onSaveClicked(position, holder.bv.tilText.editText?.text.toString(), isStep)
        }
    }

    class VH private constructor(val bv: ItemListEditableBinding) :
        RecyclerView.ViewHolder(bv.root) {
        companion object {
            fun from(parent: ViewGroup): VH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val bv = ItemListEditableBinding.inflate(layoutInflater, parent, false)
                return VH(bv)
            }
        }
    }

    interface Listener {
        fun onSaveClicked(pos: Int, text: String, isStep: Boolean)
    }
}