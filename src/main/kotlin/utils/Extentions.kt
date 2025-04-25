package utils

import data.models.Meal

fun List<Meal>.displayMeals(){
    this.forEach {meal->
        println("Name: ${meal.name}")
        println("ID: ${meal.id}")
        println("Description: ${meal.description ?: "No description available"}")
        println("Tags: ${meal.tags.joinToString(", ")}")
        println("Ingredients: ${meal.ingredients}")
        println("---")
    }
}