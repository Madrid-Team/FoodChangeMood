package presentation.features

import data.models.Meal
import data.utilities.MealsExceptions
import logic.usecase.GetFoodByAddDateUseCase
import presentation.common.BaseUIController
import presentation.common.Reader
import presentation.common.Viewer

class SearchMealsByDateUI(
    private val getFoodByAddDateUseCase: GetFoodByAddDateUseCase,
    private val viewer: Viewer,
    private val reader: Reader
) : BaseUIController {
    override val id: Int = 8
    override val message: String =
        "$id- Add a date and get list of meals added on this date.\n" +
                "- Enter the Id of any meal and you will get more details about it."

    override fun start() {
        viewer.show("===== Search Meals by Date =====")

        while (true) {
            try {
                viewer.show("\nEnter date (yyyy-MM-dd) or 'exit' to quit:")
                val inputDate = reader.getUserInput()?.trim() ?: ""

                if (inputDate.equals("exit", ignoreCase = true)) {
                    break
                }

                val meals = getFoodByAddDateUseCase(inputDate)
                displayMealsList(meals)

                viewer.show("\nEnter a meal ID to view details or 'back' to search another date:")
                val idInput = reader.getUserInput()?.trim() ?: ""

                if (idInput.equals("back", ignoreCase = true)) {
                    continue
                }

                try {
                    val mealId = idInput.toInt()
                    val meal = meals.find { it.id == mealId }
                        ?: throw MealsExceptions.MealNotFoundException("Meal with ID $mealId not found")
                    displayMealDetails(meal)
                } catch (e: NumberFormatException) {
                    viewer.show("Invalid ID format. Please enter a number.")
                } catch (e: MealsExceptions.MealNotFoundException) {
                    viewer.show(e.message)
                }

            } catch (e: MealsExceptions.InvalidDateFormatException) {
                viewer.show("Error: ${e.message}")
            } catch (e: MealsExceptions.DateParseException) {
                viewer.show("Error: ${e.message}")
            } catch (e: MealsExceptions.MealNotFoundException) {
                viewer.show("No meals were found for the given date.")
            } catch (e: Exception) {
                viewer.show("An unexpected error occurred: ${e.message}")
            }
        }

        viewer.show("Exiting meal search...")
    }

    private fun displayMealsList(meals: List<Meal>) {
        viewer.show("\n=== Meals found: ${meals.size} ===")
        viewer.show("ID\t| Name")
        viewer.show("-".repeat(50))

        meals.forEach { meal ->
            viewer.show("${meal.id}\t| ${meal.name}")
        }
    }

    private fun displayMealDetails(meal: Meal) {
        viewer.show("\n===== Meal Details =====")
        viewer.show("ID: ${meal.id}")
        viewer.show("Name: ${meal.name}")
        viewer.show("Description: ${meal.description ?: "No description available"}")
        viewer.show("Minutes to prepare: ${meal.minutes}")
        viewer.show("Submitted on: ${meal.submitted}")

        viewer.show("\nTags: ${meal.tags.joinToString(", ")}")

        viewer.show("\nNutrition Information:")
        viewer.show("Calories: ${meal.nutrition.calories}")
        viewer.show("Protein: ${meal.nutrition.protein}g")
        viewer.show("Fat: ${meal.nutrition.totalFat}g")
        viewer.show("SaturatedFat Fat: ${meal.nutrition.saturatedFat}g")
        viewer.show("Sodium: ${meal.nutrition.sodium}mg")
        viewer.show("\nIngredients:")
        meal.ingredients.ingredients.forEachIndexed { index, ingredient ->
            viewer.show("${index + 1}. $ingredient")
        }

        viewer.show("\nSteps:")
        meal.steps.steps.forEachIndexed { index, step ->
            viewer.show("${index + 1}. $step")
        }
    }
}