package my.farhan.tasty.ui.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import my.farhan.tasty.R
import my.farhan.tasty.databinding.ActivityRecipeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeActivity : AppCompatActivity() {
    private val recipeVM by viewModel<RecipeVM>()
    private lateinit var bv: ActivityRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bv = DataBindingUtil.setContentView(this, R.layout.activity_recipe)
        bv.vm = recipeVM
        bv.activity = this
        bv.lifecycleOwner = this
    }
}