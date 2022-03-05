package my.farhan.tasty.ui.recipeeditable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.farhan.tasty.repo.RecipeRepo

class RecipeEditableVM(private val recipeRepo: RecipeRepo) : ViewModel() {
    val selectedRecipe = recipeRepo.getRecipe()

    fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            recipeRepo.getRecipe(recipeId)
        }
    }
}