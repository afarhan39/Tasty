package my.farhan.tasty.data.db

import androidx.room.*
import my.farhan.tasty.data.model.Recipe

/***
 * DAO (Data Access Object) to Access [Recipe]
 */
@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe")
    suspend fun findAll(): List<Recipe>

    @Query("SELECT * FROM Recipe WHERE recipeType = :recipeType")
    suspend fun findAllWithType(recipeType: String): List<Recipe>

    @Query("SELECT * FROM Recipe WHERE recipeId = :recipeId")
    suspend fun findRecipe(recipeId: Int): Recipe

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(recipe: Recipe)

    @Update
    fun updateRecipe(vararg recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(vararg recipe: Recipe): Int
}