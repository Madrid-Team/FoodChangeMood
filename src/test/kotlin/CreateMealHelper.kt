import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import java.util.*

fun createMeals(
    name: String
) = Meal(
    name = name,
    id = 0,
    minutes = 1,
    tags = listOf("tag1", "tag2"),
    description = "description",
    nutrition = Nutrition(
        calories = 1.0,
        totalFat = 1.0,
        sugar = 1.0,
        sodium = 1.0,
        saturatedFat = 1.0,
        carbohydrates = 1.0,
        protein = 1.0
    ),
    steps = Steps(
        steps = listOf("Step1", "Step2"),
        stepsCount = 2
    ),
    ingredients = Ingredients(
        ingredients = listOf("ingredients1", "ingredients2"),
        ingredientsCount = 2
    ),
    submitted = Date(),
    contributorId = 1
)

fun createMeals(
    name: String,
    tags: List<String>,
    description: String
) = Meal(
    name = name,
    id = 1,
    minutes = 2,
    tags = tags,
    description = description,
    nutrition = Nutrition(
        calories = 1.0,
        totalFat = 1.0,
        sugar = 1.0,
        sodium = 1.0,
        saturatedFat = 1.0,
        carbohydrates = 1.0,
        protein = 1.0
    ),
    steps = Steps(
        steps = listOf("Step1", "Step2"),
        stepsCount = 2
    ),
    ingredients = Ingredients(
        ingredients = listOf("ingredients1", "ingredients2"),
        ingredientsCount = 2
    ),
    submitted = Date(),
    contributorId = 1
)