package logic.usecase

import data.csvHandler.Tags.MealFilters.HIGH_CALORIES
import data.models.Meal
import logic.Repository.MealsRepository

class SuggestMealWithHighCalorieUseCase(private val mealsRepository: MealsRepository) {

    fun suggestRandomHighCalorieMeal(alreadySuggested: Set<Int>): Meal {
        return mealsRepository.getAllMeals()
            .asSequence()
            .filter(::isHighCalorie)
            .filter { it.id !in alreadySuggested }
            .toList()
            .randomOrNull() ?: throw NoSuchElementException("No high calorie meal available to suggest")
    }

    private fun isHighCalorie(meal: Meal): Boolean {
        return meal.nutrition.calories > HIGH_CALORIES && meal.description != null
    }


}