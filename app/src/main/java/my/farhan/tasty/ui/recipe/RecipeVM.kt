package my.farhan.tasty.ui.recipe

import androidx.lifecycle.ViewModel
import my.farhan.tasty.repo.RecipeRepo

class RecipeVM(private val recipeRepo: RecipeRepo) : ViewModel() {
    val selectedRecipe = recipeRepo.getRecipe()
}