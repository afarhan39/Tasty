package my.farhan.tasty.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.farhan.tasty.repo.RecipeRepo

class HomeVM(private val recipeRepo: RecipeRepo) : ViewModel() {
    val sortOptionList = listOf("All", "Western", "Asian", "Dessert")
    val selectedSortOption = MutableLiveData(sortOptionList[0])
    val recipes = recipeRepo.getRecipes()

    fun sortBy(sortMethod: String) {
        viewModelScope.launch(Dispatchers.IO) {
            selectedSortOption.postValue(sortMethod)
            recipeRepo.sortBy(sortMethod)
        }
    }
}