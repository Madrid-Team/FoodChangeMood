package logic.helper

import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import java.util.*


fun createMeal(
    id:Int,
    name: String,
    minutes: Int = 1,
) = Meal(
    id = id,
    name = name,
    minutes = minutes,
    tags = emptyList(),
    description = "",
    nutrition = Nutrition(1.0,1.0,1.0,1.0,1.0,1.0,1.0,),
    steps = Steps(steps = emptyList() , stepsCount = 0),
    ingredients = Ingredients(ingredients = emptyList(), ingredientsCount = 0),
    submitted = Date(0),
    contributorId = 0,
)