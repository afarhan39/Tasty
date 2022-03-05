package my.farhan.tasty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int,
    var title: String,
    var description: String,
    var recipeType: String,
    var imageUrl: String,
    var ingredients: List<String> = emptyList(),
    var steps: List<String> = emptyList()
)