package my.farhan.tasty.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import my.farhan.tasty.data.db.RecipeDao
import my.farhan.tasty.data.model.Recipe

class RecipeRepo(private val dao: RecipeDao) {
    private val _recipes = MutableLiveData<List<Recipe>>()
    private val _recipe = MutableLiveData<Recipe>()

    fun getRecipes(): LiveData<List<Recipe>> = _recipes
    fun getRecipe(): LiveData<Recipe> = _recipe
    fun getMutableRecipe(): MutableLiveData<Recipe> = _recipe

    suspend fun filterBy(filter: String) {
        if (filter == "All")
            _recipes.postValue(dao.findAll())
        else
            _recipes.postValue(dao.findAllWithType(filter))
    }

    suspend fun getRecipe(recipeId: Int) {
        val recipe = dao.findRecipe(recipeId)?: Recipe(ingredients = listOf(""), steps = listOf(""))
        _recipe.postValue(recipe)
    }

    suspend fun deleteRecipe() {
        _recipe.value?.let {
            dao.deleteRecipe(it)
        }
    }

    suspend fun saveRecipe() {
        _recipe.value?.let {
            if (it.recipeId == 0)
                createRecipe(it)
            else
                updateRecipe(it)
        }
    }

    private suspend fun createRecipe(recipe: Recipe) {
        dao.add(recipe)
    }

    private suspend fun updateRecipe(recipe: Recipe) {
        dao.updateRecipe(recipe)
    }
}