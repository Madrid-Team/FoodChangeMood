package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class SuggestMealWithHighCalorieUseCase(private val mealsRepository: MealsRepository) {

    fun suggestRandomHighCalorieMeal(): Meal? {
        return mealsRepository.getAllMeals()
            .asSequence()
            .filter{it.nutrition.calories > 700}
            .shuffled()
            .firstOrNull()
    }
}