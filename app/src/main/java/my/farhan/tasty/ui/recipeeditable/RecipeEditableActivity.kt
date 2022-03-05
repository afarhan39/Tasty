package my.farhan.tasty.ui.recipeeditable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import my.farhan.tasty.R
import my.farhan.tasty.databinding.ActivityRecipeEditableBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeEditableActivity : AppCompatActivity() {
    private val recipeEditableVM by viewModel<RecipeEditableVM>()
    private lateinit var bv: ActivityRecipeEditableBinding


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
        recipeEditableVM.getRecipe(recipeId)

        bv = DataBindingUtil.setContentView(this, R.layout.activity_recipe_editable)
        bv.vm = recipeEditableVM
        bv.activity = this
        bv.lifecycleOwner = this
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.discard_title))
            .setMessage(resources.getString(R.string.discard_message))
            .setNegativeButton(resources.getString(R.string.cancel_cta)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.discard_cta)) { dialog, _ ->
                dialog.dismiss()
                super.onBackPressed()
            }
            .show()
    }

    fun deleteRecipe() {
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.delete_recipe_title))
            .setMessage(resources.getString(R.string.delete_recipe_message, recipeEditableVM.selectedRecipe.value?.title))
            .setNegativeButton(resources.getString(R.string.cancel_cta)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.delete_cta)) { dialog, _ ->
                dialog.dismiss()
                recipeEditableVM.deleteRecipe()
            }
            .show()
    }
}