package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class SuggestMealWithHighCalorieUseCase(private val mealsRepository: MealsRepository) {

    fun suggestRandomHighCalorieMeal(alreadySuggested: Set<Int>): Meal? {
        return mealsRepository.getAllMeals()
            .asSequence()
            .filter(::isHighCalorie)
            .filter { it.id !in alreadySuggested }
            .toList()
            .randomOrNull()
    }

    private fun isHighCalorie(meal: Meal): Boolean {
        return meal.nutrition.calories > HIGH_CALORIES && meal.description != null
    }

    companion object{
        const val HIGH_CALORIES = 700
    }
}