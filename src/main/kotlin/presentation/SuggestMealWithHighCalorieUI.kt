package presentation

import logic.usecase.SuggestMealWithHighCalorieUseCase

class SuggestMealWithHighCalorieUI(private val suggestMealWithHighCalorieUseCase: SuggestMealWithHighCalorieUseCase) {
    private val alreadySuggested = mutableSetOf<Int>()

    fun suggestHighCalorieMeal() {
        println("Welcome! suggest a high calorie meal.")
        println("If you like the suggested meal, type:\n- like (to see more details)\n- dislike (to get another suggestion)")


        while (true) {
            val meal = suggestMealWithHighCalorieUseCase.suggestRandomHighCalorieMeal(alreadySuggested)

            if (meal == null) {
                println("No more high calorie meals to suggest")
                break
            }

            alreadySuggested.add(meal.id)
            println("Suggested Meal: ${meal.name} (${meal.nutrition.calories} calories)")
            println("Do you like this meal? (like - dislike): ")
            when (readlnOrNull()?.lowercase()) {
                "like" -> {
                    println("Here are the full details of the meal:")
                    println("Name: ${meal.name}")
                    println("Description: ${meal.description ?: "No description available"}")
                    println("Calories: ${meal.nutrition.calories}")
                    println("Time: ${meal.minutes} minutes")
                    break
                }

                "dislike" -> {
                    println("Okay, let me suggest another meal.\n")
                }

                else -> {
                    println("Invalid input. Please type 'like', or 'dislike'")
                }
            }
        }
    }
}