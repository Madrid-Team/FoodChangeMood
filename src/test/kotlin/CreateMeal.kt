import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import java.util.*

fun createMeal(
    name: String = "Name",
    id: Int = 0,
    minutes: Int = 15,
    tags: List<String> = listOf("TAG_1", "TAG_2", "TAG_3"),
    calories: Double = 10.0,
    totalFat: Double = 10.0,
    sugar: Double = 10.0,
    sodium: Double = 10.0,
    saturatedFat: Double = 10.0,
    carbohydrates: Double = 10.0,
    protein: Double = 10.0,
    stepsCount: Int = 3,
    steps: List<String> = listOf("STEP_1", "STEP_2", "STEP_3"),
    ingredientsCount: Int = 3,
    description: String? = "description",
    submitted: Date = Date(),
    contributorId: Int = 0,
    ingredients: List<String> = listOf("INGREDIENTS_1", "INGREDIENTS_2", "INGREDIENTS_3"),
) = Meal(
    name = name,
    id = id,
    minutes = minutes,
    tags = tags,
    description = description,
    nutrition = Nutrition(
        calories = calories,
        totalFat = totalFat,
        sugar = sugar,
        sodium = sodium,
        saturatedFat = saturatedFat,
        carbohydrates = carbohydrates,
        protein = protein,
    ),
    steps = Steps(
        stepsCount = stepsCount,
        steps = steps,
    ),
    ingredients = Ingredients(
        ingredientsCount = ingredientsCount,
        ingredients = ingredients,
    ),
    submitted = submitted,
    contributorId = contributorId,
)