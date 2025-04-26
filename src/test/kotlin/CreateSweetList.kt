import data.models.Meal


fun createMealsForSweetWithoutEggs(): List<Meal> {
    return listOf(
        createMeal(
            name = "fish",
            tags = listOf("vegetarian", "mexican", "fall", "easy"),
            ingredients = listOf("winter squash", "mexican seasoning", "egg", "butter", "olive oil", "salt"),
        ),
        createMeal(
            name = "Mexican Baked Squash",
            tags = listOf("vegetarian", "mexican", "fall", "easy", "sweet"),
            ingredients = listOf("winter squash", "mexican seasoning", "butter", "olive oil", "salt")
        )
    )
}

fun createMealsForIgnoreCaseTest(): List<Meal> {
    return listOf(
        createMeal(
            name = "Omelette",
            tags = listOf("Sweet"),
            ingredients = listOf("Egg", "cheese", "milk")
        ),
        createMeal(
            name = "Fruit Salad",
            tags = listOf("SWEET"),
            ingredients = listOf("banana", "apple", "honey")
        )
    )
}

fun createMealsForSubstringTest(): List<Meal> {
    return listOf(
        createMeal(
            name = "Chocolate Mousse",
            tags = listOf("sweet dessert"),
            ingredients = listOf("chocolate", "egg whites", "cream")
        ),
        createMeal(
            name = "Breakfast Bowl",
            tags = listOf("SWEET"),
            ingredients = listOf("boiled egg", "yogurt", "granola")
        ),
        createMeal(
            name = "Mango Pudding",
            tags = listOf("light sweet"),
            ingredients = listOf("mango", "milk", "sugar")
        )
    )
}

fun createMealsFreeOfSweetWithoutEggs(): List<Meal> {
    return listOf(
        createMeal(
            name = "Mexican Baked Squash",
            tags = listOf("vegetarian", "mexican", "fall", "easy"),
            ingredients = listOf("winter squash", "mexican seasoning", "egg", "butter", "olive oil", "salt")
        ),
        createMeal(
            name = "fish",
            tags = listOf("vegetarian", "mexican", "fall", "easy"),
            ingredients =
            listOf("winter squash", "mexican seasoning", "butter", "olive oil", "salt")
        )
    )
}

