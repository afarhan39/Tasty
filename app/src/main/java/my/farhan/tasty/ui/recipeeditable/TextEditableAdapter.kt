package my.farhan.tasty.ui.recipeeditable

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.farhan.tasty.databinding.ItemListEditableBinding
import my.farhan.tasty.util.multilineIme
import my.farhan.tasty.util.on

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
        holder.bv.item = item
        holder.bv.isStep = isStep

        holder.bv.tilText.setEndIconOnClickListener {
            listener.onDeleteClicked(holder.bv.tilText.editText?.text.toString(), isStep)
        }

        val et = holder.bv.tilText.editText
        et?.let {
            et.multilineIme(EditorInfo.IME_ACTION_DONE)
            et.on(EditorInfo.IME_ACTION_DONE) { onClickDone(holder, position) }
        }
    }

    private fun onClickDone(holder: VH, position: Int) {
        val text = holder.bv.tilText.editText?.text.toString()
        if (text.isBlank()) return

        listener.onSaveClicked(position, text, isStep)
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
        fun onDeleteClicked(text: String, isStep: Boolean)
    }
}