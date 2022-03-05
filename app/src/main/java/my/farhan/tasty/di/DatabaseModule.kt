package my.farhan.tasty.di

import android.app.Application
import androidx.room.Room
import my.farhan.tasty.data.db.RecipeDao
import my.farhan.tasty.data.db.TastyDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): TastyDatabase {
        return Room.databaseBuilder(application, TastyDatabase::class.java, "tasty")
            .fallbackToDestructiveMigration()
            .createFromAsset("database/tasty.db")
            .build()
    }

    fun provideMovieDao(database: TastyDatabase): RecipeDao {
        return database.recipeDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideMovieDao(get()) }
}