package presentation

import data.models.Meal
import data.utilities.MealsExceptions
import logic.usecase.GetFoodByAddDateUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchMealsByDateUI : KoinComponent {
    private val getFoodByAddDateUseCase: GetFoodByAddDateUseCase by inject()

    fun start() {
        println("===== Search Meals by Date =====")

        while (true) {
            try {
                println("\nEnter date (yyyy-MM-dd) or 'exit' to quit:")
                val inputDate = readlnOrNull()?.trim() ?: ""

                if (inputDate.equals("exit", ignoreCase = true)) {
                    break
                }
                try {


                    val meals = getFoodByAddDateUseCase(inputDate)
                    displayMealsList(meals)

                    println("\nEnter a meal ID to view details or 'back' to search another date:")
                    val idInput = readlnOrNull()?.trim() ?: ""

                    if (idInput.equals("back", ignoreCase = true)) {
                        continue
                    }

                    val mealId = idInput.toInt()
                    val meal = meals.find { it.id == mealId }
                        ?: throw MealsExceptions.MealNotFoundException("Meal with ID $mealId not found")
                    displayMealDetails(meal)
                } catch (_: NumberFormatException) {
                    println("Invalid ID format. Please enter a number.")
                } catch (e: MealsExceptions.MealNotFoundException) {
                    println(e.message)
                }

            } catch (e: MealsExceptions.InvalidDateFormatException) {
                println("Error: ${e.message}")
            } catch (e: MealsExceptions.DateParseException) {
                println("Error: ${e.message}")
            } catch (_: MealsExceptions.MealNotFoundException) {
                println("No meals were found for the given date.")
            } catch (e: Exception) {
                println("An unexpected error occurred: ${e.message}")
            }
        }

        println("Exiting meal search...")
    }

    private fun displayMealsList(meals: List<Meal>) {
        println("\n=== Meals found: ${meals.size} ===")
        println("ID\t| Name")
        println("-".repeat(50))

        meals.forEach { meal ->
            println("${meal.id}\t| ${meal.name}")
        }
    }

    private fun displayMealDetails(meal: Meal) {
        println("\n===== Meal Details =====")
        println("ID: ${meal.id}")
        println("Name: ${meal.name}")
        println("Description: ${meal.description ?: "No description available"}")
        println("Minutes to prepare: ${meal.minutes}")
        println("Submitted on: ${meal.submitted}")

        println("\nTags: ${meal.tags.joinToString(", ")}")

        println("\nNutrition Information:")
        println("Calories: ${meal.nutrition.calories}")
        println("Protein: ${meal.nutrition.protein}g")
        println("Fat: ${meal.nutrition.totalFat}g")
        println("SaturatedFat Fat: ${meal.nutrition.saturatedFat}g")
        println("Sodium: ${meal.nutrition.sodium}mg")
        println("\nIngredients:")
        meal.ingredients.ingredients.forEachIndexed { index, ingredient ->
            println("${index + 1}. $ingredient")
        }

        println("\nSteps:")
        meal.steps.steps.forEachIndexed { index, step ->
            println("${index + 1}. $step")
        }
    }
}