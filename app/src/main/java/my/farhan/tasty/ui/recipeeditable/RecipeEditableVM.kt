package my.farhan.tasty.ui.recipeeditable

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.farhan.tasty.R
import my.farhan.tasty.data.model.Recipe
import my.farhan.tasty.repo.RecipeRepo
import my.farhan.tasty.ui.home.HomeActivity
import kotlin.random.Random

class RecipeEditableVM(private val recipeRepo: RecipeRepo, private val application: Application) :
    ViewModel() {
    val selectedRecipe = recipeRepo.getMutableRecipe()
    private val res = application.resources
    private val randomImageList = res.getStringArray(R.array.randomImageUrl).toList()
    val recipeType = res.getStringArray(R.array.recipeType).drop(1).toList()
    val finishActivityEvent = MutableLiveData<Any>()

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
        if (isValid()) {
            viewModelScope.launch(Dispatchers.IO) {
                recipeRepo.saveRecipe()
                finishActivityEvent.postValue(Any())
            }
        }
    }

    private fun goToHome() {
        val intent = Intent(application, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        application.startActivity(intent)
    }

    fun randomizeImage() {
        selectedRecipe.value = selectedRecipe.value?.also {
            it.imageUrl = randomImageList[Random.nextInt(0, randomImageList.size)]
        }
    }

    private fun isValid(): Boolean {
        selectedRecipe.value?.let {
            val errorMessage = getErrorMessage(it) ?: return true
            Toast.makeText(application, errorMessage, Toast.LENGTH_SHORT).show()
            return false
        }
        return false
    }

    private fun getErrorMessage(recipe: Recipe): String? {
        if (recipe.title.isEmpty()) return res.getString(R.string.title_required)
        if (recipe.description.isEmpty()) return res.getString(R.string.description_required)
        if (recipe.imageUrl.isEmpty()) return res.getString(R.string.image_required)
        if (recipe.recipeType.isEmpty()) return res.getString(R.string.type_required)
        if (recipe.ingredients.isEmpty()) return res.getString(R.string.ingredient_required)
        if (recipe.ingredients.last().isBlank()) return res.getString(R.string.ingredient_required)
        if (recipe.steps.isEmpty()) return res.getString(R.string.step_required)
        if (recipe.steps.last().isBlank()) return res.getString(R.string.step_required)

        return null
    }

    fun addIngredients() {
        val temp = selectedRecipe.value ?: return
        if (temp.ingredients.isNotEmpty() && temp.ingredients.last().isBlank()) return

        selectedRecipe.value = temp.also {
            val arrayList = ArrayList(it.ingredients)
            arrayList.add("")
            it.ingredients = arrayList
        }
    }

    fun addSteps() {
        val temp = selectedRecipe.value ?: return
        if (temp.steps.isNotEmpty() && temp.steps.last().isBlank()) return

        selectedRecipe.value = selectedRecipe.value?.also {
            val arrayList = ArrayList(it.steps)
            arrayList.add("")
            it.steps = arrayList
        }
    }

    fun updateIngredients(pos: Int, text: String) {
        if (text.isBlank()) return
        selectedRecipe.value = selectedRecipe.value?.also {
            val arrayList = ArrayList(it.ingredients)
            arrayList[pos] = text
            it.ingredients = arrayList
        }
    }

    fun updateSteps(pos: Int, text: String) {
        if (text.isBlank()) return
        selectedRecipe.value = selectedRecipe.value?.also {
            val arrayList = ArrayList(it.steps)
            arrayList[pos] = text
            it.steps = arrayList
        }
    }

    fun deleteItem(text: String, isStep: Boolean) {
        selectedRecipe.value = selectedRecipe.value?.also {
            val arrayList = when (isStep) {
                true -> ArrayList(it.steps)
                false -> ArrayList(it.ingredients)
            }
            arrayList.remove(text)

            if (isStep)
                it.steps = arrayList
            else
                it.ingredients = arrayList
        }
    }
}