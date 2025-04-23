package logic.usecase

import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import java.util.*

fun createSweet(name: String, tags: List<String>, ingredients: Ingredients) = Meal(
    name = name,
    id = 1,
    minutes = 55,
    tags = tags,
    description = "Autumn is my favorite time of year to cook! This recipe can be spicy or sweet — your choice!",
    nutrition = Nutrition(
        calories = 2.7,
        totalFat = 5.0,
        sugar = 8.0,
        sodium = 300.0,
        protein = 8.9,
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
    ingredients = ingredients,
    submitted = Date(),
    contributorId = 47892
)

fun createMealsForSweetWithoutEggs(): List<Meal> {
    return listOf(
        createSweet(
            name = "fish",
            tags = listOf("vegetarian", "mexican", "fall", "easy"),
            ingredients = Ingredients(
                listOf("winter squash", "mexican seasoning", "egg", "butter", "olive oil", "salt"),
                6
            )
        ),
        createSweet(
            name = "Mexican Baked Squash",
            tags = listOf("vegetarian", "mexican", "fall", "easy", "SWEET"),
            ingredients = Ingredients(
                listOf("winter squash", "mexican seasoning", "butter", "olive oil", "salt"),
                6
            )
        )
    )
}

fun createMealsForIgnoreCaseTest(): List<Meal> {
    return listOf(
        createSweet(
            name = "Omelette",
            tags = listOf("Sweet"),
            ingredients = Ingredients(listOf("Egg", "cheese", "milk"), 3)
        ),
        createSweet(
            name = "Fruit Salad",
            tags = listOf("SWEET"),
            ingredients = Ingredients(listOf("banana", "apple", "honey"), 3)
        )
    )
}

fun createMealsForSubstringTest(): List<Meal> {
    return listOf(
        createSweet(
            name = "Chocolate Mousse",
            tags = listOf("sweet dessert"),
            ingredients = Ingredients(listOf("chocolate", "egg whites", "cream"), 3)
        ),
        createSweet(
            name = "Breakfast Bowl",
            tags = listOf("SWEET"),
            ingredients = Ingredients(listOf("boiled egg", "yogurt", "granola"), 3)
        ),
        createSweet(
            name = "Mango Pudding",
            tags = listOf("light sweet"),
            ingredients = Ingredients(listOf("mango", "milk", "sugar"), 3)
        )
    )
}

fun createMealsFreeOfSweetWithoutEggs(): List<Meal> {
    return listOf(
        createSweet(
            name = "Mexican Baked Squash",
            tags = listOf("vegetarian", "mexican", "fall", "easy"),
            ingredients = Ingredients(
                listOf("winter squash", "mexican seasoning", "egg", "butter", "olive oil", "salt"),
                6
            )
        ),
        createSweet(
            name = "fish",
            tags = listOf("vegetarian", "mexican", "fall", "easy"),
            ingredients = Ingredients(
                listOf("winter squash", "mexican seasoning", "butter", "olive oil", "salt"),
                6
            )
        )
    )
}

