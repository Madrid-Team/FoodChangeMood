package logic.usecase

import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import java.util.Date

fun createTestMeal(id: Int, calories: Double, description: String?): Meal {
    return Meal(
        name = "",
        id = id,
        minutes = 30,
        tags = listOf(),
        description = description,
        nutrition = Nutrition(
            calories = calories,
            totalFat = 10.0,
            sugar = 20.0,
            sodium = 300.0,
            saturatedFat = 5.0,
            carbohydrates = 50.0,
            protein = 25.0
        ),
        steps = Steps(listOf(), 2),
        ingredients = Ingredients(listOf(), 2),
        submitted = Date(),
        contributorId = 1,
    )
}
