package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class ShowRandomMealsIncludePotatoesUseCase(
    private val mealsRepository: MealsRepository
) {
    fun showRandomMealsIncludePotatoes(): List<Meal> {
        return mealsRepository.getAllMeals()
            .filter(::isContainsPotato)
            .shuffled()
            .take(10)
    }

    private fun isContainsPotato(meal: Meal): Boolean {
        return meal.ingredients.ingredients.any { it.contains("potato", ignoreCase = true) }
    }
}