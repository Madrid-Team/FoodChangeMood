package presentation.features

import data.models.Meal
import logic.usecase.SuggestMealWithHighCalorieUseCase
import presentation.common.BaseUIController
import presentation.common.Reader
import presentation.common.Viewer

class SuggestMealWithHighCalorieUI(
    private val suggestMealWithHighCalorieUseCase: SuggestMealWithHighCalorieUseCase,
    private val viewer: Viewer,
    private val reader: Reader
) : BaseUIController {

    override val id: Int = 13
    override val message: String =
        "$id- Get one sweet that not contains no eggs .. \n" +
                "- Write yes if you like it and want more details about this meal.\n" +
                "- Write no if you dislike it and want another sweet."

    override fun start() {
        suggestHighCalorieMeal()
    }

    private val alreadySuggested = mutableSetOf<Int>()

    private fun suggestHighCalorieMeal() {
        greetUser()

        while (true) {
            val meal = suggestMealWithHighCalorieUseCase.suggestRandomHighCalorieMeal(alreadySuggested)

            if (meal == null) {
                viewer.show("No more high calorie meals to suggest")
                break
            }

            alreadySuggested.add(meal.id)
            viewer.show("Suggested Meal: ${meal.name} (${meal.nutrition.calories} calories)")
            viewer.show("Do you like this meal? (like - dislike): ")
            when (reader.getUserInput()?.lowercase()) {
                "like" -> {
                    showMealDetails(meal)
                    break
                }

                "dislike" -> {
                    viewer.show("Okay, let me suggest another meal.\n")
                }

                else -> {
                    viewer.show("Invalid input. Please type 'like', or 'dislike'")
                }
            }
        }
    }

    private fun greetUser() {
        viewer.show("Welcome! Let's suggest a high calorie meal.")
        viewer.show("Type one of the following:")
        viewer.show("- like: to see more details")
        viewer.show("- dislike: to get another suggestion")
    }

    private fun showMealDetails(meal: Meal) {
        viewer.show("Here are the full details of the meal:")
        viewer.show("Name: ${meal.name}")
        viewer.show("Description: ${meal.description ?: "No description available"}")
        viewer.show("Calories: ${meal.nutrition.calories}")
        viewer.show("Preparation Time: ${meal.minutes} minutes")
    }

}