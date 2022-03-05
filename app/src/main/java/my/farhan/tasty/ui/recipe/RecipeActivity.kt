package my.farhan.tasty.ui.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import my.farhan.tasty.R
import my.farhan.tasty.databinding.ActivityRecipeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeActivity : AppCompatActivity() {
    private val recipeVM by viewModel<RecipeVM>()
    private lateinit var bv: ActivityRecipeBinding
    private lateinit var ingredientsAdapter: TextAdapter
    private lateinit var stepsAdapter: TextAdapter


    companion object {
        const val EXTRA_RECIPE_ID = "EXTRA_RECIPE_ID"

        @JvmStatic
        fun getBundle(recipeId: Int): Bundle {
            return bundleOf(
                EXTRA_RECIPE_ID to recipeId
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recipeId = intent.extras!!.getInt(EXTRA_RECIPE_ID)
        recipeVM.getRecipe(recipeId)

        bv = DataBindingUtil.setContentView(this, R.layout.activity_recipe)
        bv.vm = recipeVM
        bv.activity = this
        bv.lifecycleOwner = this

        setAdapter()
    }

    private fun setAdapter() {
        ingredientsAdapter = TextAdapter()
        stepsAdapter = TextAdapter()

        bv.rvIngredients.adapter = ingredientsAdapter
        bv.rvSteps.adapter = stepsAdapter

        recipeVM.selectedRecipe.observe(this) {
            it?.let {
                ingredientsAdapter.submitList(it.ingredients)
                stepsAdapter.submitList(it.steps)
            }
        }
    }
}