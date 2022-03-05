package my.farhan.tasty.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import my.farhan.tasty.R
import my.farhan.tasty.databinding.ActivityHomeBinding
import my.farhan.tasty.ui.recipe.RecipeActivity
import my.farhan.tasty.ui.recipeeditable.RecipeEditableActivity
import my.farhan.tasty.util.SpacesItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), RecipesAdapter.Listener {
    private val homeVM by viewModel<HomeVM>()
    private lateinit var bv: ActivityHomeBinding
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var sortPopup: PopupMenu

    /***
     * [SortMethod.ReleaseDate] is set by default
     * here all view is also set, such as [initPopup] and [setAdapter]
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bv = DataBindingUtil.setContentView(this, R.layout.activity_home)
        bv.vm = homeVM
        bv.activity = this
        bv.lifecycleOwner = this

        initPopup()
        setAdapter()
    }

    override fun onResume() {
        super.onResume()
        homeVM.filterBy(homeVM.selectedFilter.value ?: homeVM.filterList[0])
    }

    override fun onClickRecipe(recipeId: Int) {
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtras(RecipeActivity.getBundle(recipeId))
        startActivity(intent)
    }

    /***
     * [initPopup] use list of [SortMethod.label] to populate menu
     * and set to new [SortMethod] onMenuItemClick
     */
    private fun initPopup() {
        sortPopup = PopupMenu(this, bv.clFilter)
        for (item in homeVM.filterList)
            sortPopup.menu.add(item)

        sortPopup.setOnMenuItemClickListener { menuItem: MenuItem ->
            homeVM.filterBy(menuItem.toString())
            true
        }
    }

    /***
     * exposed method to [ActivityMovieListBinding.clSort] to show popup
     */
    fun showSortPopup() {
        sortPopup.show()
    }

    /***
     * [ActivityMovieListBinding.rvMovies] is set to StaggeredGridLayoutManager, which have 2 column
     * [SpacesItemDecoration] is used to have spacing in all directions
     * [DefaultItemAnimator] is used to have some pleasant UI when load
     * [RecipesAdapter.submitList] is used to set new list to adapter whenever liveData changes
     * [HomeVM.apiEvent] will determine when [ActivityMovieListBinding.svContainer] is finished loading
     */
    private fun setAdapter() {
        recipesAdapter = RecipesAdapter(this)
        bv.rvRecipes.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        bv.rvRecipes.adapter = recipesAdapter
        val decoration = SpacesItemDecoration(16)
        bv.rvRecipes.itemAnimator = DefaultItemAnimator()
        bv.rvRecipes.addItemDecoration(decoration)
        homeVM.recipes.observe(this) {
            if (it.isNotEmpty() && it != null) {
                recipesAdapter.submitList(it)
            }
        }
    }

    fun createNewRecipe() {
        val intent = Intent(this, RecipeEditableActivity::class.java)
        startActivity(intent)
    }
}