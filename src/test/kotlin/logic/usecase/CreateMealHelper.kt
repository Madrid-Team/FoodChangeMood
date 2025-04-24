package logic.usecase

import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import java.util.*

fun createMeal(calories: Double, protein: Double) = Meal(
        name = "Mexican Baked Squash",
        id = 1,
        minutes = 55,
        tags = listOf("vegetarian", "mexican", "fall", "easy", "sweet"),
        description = "Autumn is my favorite time of year to cook! This recipe can be spicy or sweet — your choice!",
        nutrition = Nutrition(
            calories = calories,
            totalFat = 5.0,
            sugar = 8.0,
            sodium = 300.0,
            protein = protein,
            saturatedFat = 2.0,
            carbohydrates = 40.0
        ),
        steps = Steps(
            listOf(
                "Preheat oven to 350°F",
                "Cut squash in half and remove seeds",
                "Drizzle with butter or olive oil",
                "Season with either sweet or spicy mix",
                "Bake for 40-60 minutes until tender"
            ), 5
        ),
        ingredients = Ingredients(
            listOf("winter squash", "mexican seasoning", "egg", "butter", "olive oil", "salt"),
            6
        ),
        submitted = Date(),
        contributorId = 47892
    )
