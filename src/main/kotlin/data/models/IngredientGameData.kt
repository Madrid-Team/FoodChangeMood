package data.models

data class IngredientGameData(
    val meal: Meal,
    val options: List<String>,
    val correctAnswer: String
)