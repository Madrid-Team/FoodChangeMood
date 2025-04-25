package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetMealsSuitableForGymUseCase(private val mealsRepository: MealsRepository) {

    fun getMealsWithinCalorieAndProteinRange(calories: Double, protein: Double): List<Meal> {
        return mealsRepository.getAllMeals()
            .filter { meal -> isMealWithinCaloriesAndProteinRange(meal, calories, protein) }
            .takeIf { it.isNotEmpty() } ?: throw NoSuchElementException("No meals found matching calories and proten even withn range 10")
    }

    private fun isMealWithinCaloriesAndProteinRange(meal: Meal, calories: Double, protein: Double): Boolean {
        val calorieRange = (calories - 10)..(calories + 10)
        val proteinRange = (protein - 10)..(protein + 10)

        return meal.nutrition.calories in calorieRange && meal.nutrition.protein in proteinRange
    }

}