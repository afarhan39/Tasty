package my.farhan.tasty.di

import my.farhan.tasty.ui.home.HomeVM
import my.farhan.tasty.ui.recipe.RecipeVM
import my.farhan.tasty.ui.recipeeditable.RecipeEditableVM
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeVM(get(), androidContext().resources) }
    viewModel { RecipeVM(get()) }
    viewModel { RecipeEditableVM(get(), androidApplication()) }
}