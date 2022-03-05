package my.farhan.tasty.di

import my.farhan.tasty.repo.RecipeRepo
import org.koin.dsl.module

val repositoryModule = module {
    single { RecipeRepo(get()) }
}