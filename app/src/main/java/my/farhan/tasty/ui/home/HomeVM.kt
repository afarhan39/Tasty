package my.farhan.tasty.ui.home

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.farhan.tasty.R
import my.farhan.tasty.repo.RecipeRepo

class HomeVM(private val recipeRepo: RecipeRepo, private val res: Resources) : ViewModel() {
    val filterList = res.getStringArray(R.array.recipeType).toList()
    val selectedFilter = MutableLiveData(filterList[0])
    val recipes = recipeRepo.getRecipes()

    fun sortBy(sortMethod: String) {
        viewModelScope.launch(Dispatchers.IO) {
            selectedFilter.postValue(sortMethod)
            recipeRepo.sortBy(sortMethod)
        }
    }
}