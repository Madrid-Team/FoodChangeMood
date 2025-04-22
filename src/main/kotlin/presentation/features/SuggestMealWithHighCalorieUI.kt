package presentation.features

import data.models.Meal
import logic.usecase.SuggestMealWithHighCalorieUseCase
import presentation.common.BaseUIController

class SuggestMealWithHighCalorieUI(
    private val suggestMealWithHighCalorieUseCase: SuggestMealWithHighCalorieUseCase
) : BaseUIController {

    override val id: Int = 13
    override val message: String =
        "6- Get one sweet that not contains no eggs .. \n" +
                "- Write yes if you like it and want more details about this meal.\n" +
                "- Write no if you dislike it and want another sweet."

    override fun start() {
        TODO("Not yet implemented")
    }

    private val alreadySuggested = mutableSetOf<Int>()

    fun suggestHighCalorieMeal() {
        greetUser()

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
                    showMealDetails(meal)
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

    private fun greetUser() {
        println("Welcome! Let's suggest a high calorie meal.")
        println("Type one of the following:")
        println("- like: to see more details")
        println("- dislike: to get another suggestion")
    }

    private fun showMealDetails(meal: Meal) {
        println("Here are the full details of the meal:")
        println("Name: ${meal.name}")
        println("Description: ${meal.description ?: "No description available"}")
        println("Calories: ${meal.nutrition.calories}")
        println("Preparation Time: ${meal.minutes} minutes")
    }

}