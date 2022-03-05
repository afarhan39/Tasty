package my.farhan.tasty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import my.farhan.tasty.data.db.Converters
import my.farhan.tasty.data.db.RecipeDao
import my.farhan.tasty.data.model.Recipe

@Database(
    entities = [Recipe::class],
    version = 1, exportSchema = false
)

@TypeConverters(Converters::class)
abstract class TastyDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao
}