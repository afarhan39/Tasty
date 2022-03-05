package my.farhan.tasty.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.farhan.tasty.data.model.Recipe
import my.farhan.tasty.databinding.ItemRecipeBinding

class RecipesAdapter(
    val listener: Listener
) :
    ListAdapter<Recipe, RecipesAdapter.VH>(Companion) {

    companion object : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.recipeId == newItem.recipeId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH.from(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.bv.item = item
        holder.bv.listener = listener
    }

    interface Listener {
        fun onClickRecipe(recipeId: Int)
    }

    class VH private constructor(val bv: ItemRecipeBinding) :
        RecyclerView.ViewHolder(bv.root) {
        companion object {
            fun from(parent: ViewGroup): VH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val bv = ItemRecipeBinding.inflate(layoutInflater, parent, false)
                return VH(bv)
            }
        }
    }
}