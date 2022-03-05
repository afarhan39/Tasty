package my.farhan.tasty.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import my.farhan.tasty.data.db.RecipeDao
import my.farhan.tasty.data.model.Recipe

/***
 * A repo to hold all API and DB transactions
 */
class RecipeRepo(private val dao: RecipeDao) {
    /***
     * _ prefix to mark it is private, and mutable
     * these data will only will mutable inside [RecipeRepo] and will be set as LiveData when exposed to ViewModel
     * such as [my.farhan.favy.ui.list.HomeVM.apiEvent]
     * and [my.farhan.favy.ui.detail.RecipeVM.selectedMovie]
     */
    private val _recipes = MutableLiveData<List<Recipe>>()
    private val _recipe = MutableLiveData<Recipe>()

    fun getRecipes(): LiveData<List<Recipe>> = _recipes
    fun getRecipe(): LiveData<Recipe> = _recipe

    /***
     * take [sortMethod] params and postValue according to [sortMethod] given
     * by default, will set to [SortMethod.ReleaseDate]
     */
    suspend fun sortBy(sortMethod: String) {
        if (sortMethod == "All")
            _recipes.postValue(dao.findAll())
        else
            _recipes.postValue(dao.findAllWithType(sortMethod))
    }

    suspend fun getRecipe(recipeId: Int) {
        _recipe.postValue(dao.findRecipe(recipeId))
    }
}