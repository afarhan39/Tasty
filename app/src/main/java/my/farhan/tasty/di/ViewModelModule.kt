package my.farhan.tasty.di

import my.farhan.tasty.ui.home.HomeVM
import my.farhan.tasty.ui.recipe.RecipeVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeVM(get()) }
    viewModel { RecipeVM(get()) }
}