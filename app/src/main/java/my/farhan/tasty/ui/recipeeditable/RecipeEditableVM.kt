package my.farhan.tasty.ui.recipeeditable

import android.app.Application
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.farhan.tasty.repo.RecipeRepo
import my.farhan.tasty.ui.home.HomeActivity

class RecipeEditableVM(private val recipeRepo: RecipeRepo, private val application: Application) : ViewModel() {
    val selectedRecipe = recipeRepo.getRecipe()

    fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            recipeRepo.getRecipe(recipeId)
        }
    }

    fun deleteRecipe() {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepo.deleteRecipe()
            goToHome()
        }
    }

    fun saveRecipe() {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepo.saveRecipe()
            goToHome()
        }

    }

    private fun goToHome() {
        val intent = Intent(application, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        application.startActivity(intent)
    }
}