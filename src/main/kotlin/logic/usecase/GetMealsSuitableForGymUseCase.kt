package logic.usecase

import data.csvHandler.Tags.GymMealParameters.CALORIES_MARGIN
import data.csvHandler.Tags.GymMealParameters.PROTEIN_MARGIN
import data.models.Meal
import logic.Repository.MealsRepository

class GetMealsSuitableForGymUseCase(private val mealsRepository: MealsRepository) {

    fun getMealsWithinCalorieAndProteinRange(calories: Double, protein: Double): List<Meal> {
        return mealsRepository.getAllMeals().filter { meal ->
            isMealWithinCaloriesAndProteinRange(meal, calories, protein)
        }
    }

    private fun isMealWithinCaloriesAndProteinRange(meal: Meal, calories: Double, protein: Double): Boolean {
        val calorieRange = (calories - CALORIES_MARGIN)..(calories + CALORIES_MARGIN)
        val proteinRange = (protein - PROTEIN_MARGIN)..(protein + PROTEIN_MARGIN)

        return meal.nutrition.calories in calorieRange && meal.nutrition.protein in proteinRange
    }


}