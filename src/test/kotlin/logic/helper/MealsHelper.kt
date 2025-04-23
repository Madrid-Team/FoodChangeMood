package logic.helper

import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import java.util.*

fun createMeal(
    id:Int = 1,
    name: String = "meal",
    minutes: Int = 1,
    tags: List<String> = emptyList(),
    description: String? = "",
    nutrition:Nutrition = Nutrition(1.0,1.0,1.0,1.0,1.0,1.0,1.0,),
    steps: Steps = Steps(steps = emptyList() , stepsCount = 0),
    ingredients: Ingredients = Ingredients(ingredients = emptyList(), ingredientsCount = 0),
    submitted: Date = Date(0),
    contributorId: Int = 0,
) = Meal(
    id = id,
    name = name,
    minutes = minutes,
    tags = tags,
    description = description,
    nutrition = nutrition,
    steps = steps,
    ingredients = ingredients,
    submitted = submitted,
    contributorId = contributorId,
)
