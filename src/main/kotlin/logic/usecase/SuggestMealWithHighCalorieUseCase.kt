package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class SuggestMealWithHighCalorieUseCase(private val mealsRepository: MealsRepository) {

    fun suggestRandomHighCalorieMeal(): Meal? {
        return mealsRepository.getAllMeals()
            .asSequence()
            .filter(::isHighCalorie)
            .shuffled()
            .firstOrNull()
    }

    private fun isHighCalorie(meal: Meal): Boolean {
        return meal.nutrition.calories > HIGH_CALORIES
    }

    companion object{
        const val HIGH_CALORIES = 700
    }
}