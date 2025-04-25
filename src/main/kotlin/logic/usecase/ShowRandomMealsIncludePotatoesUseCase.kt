package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class ShowRandomMealsIncludePotatoesUseCase(
    private val mealsRepository: MealsRepository
) {
    fun showRandomMealsIncludePotatoes(): List<Meal> {
        val filteredMealsIncludePotatoes = mealsRepository.getAllMeals()
            .filter(::isContainsPotato)
            .shuffled()
            .take(10)
        if (filteredMealsIncludePotatoes.isEmpty()) throw NoSuchElementException("Can't found meals include potatoes")
        return filteredMealsIncludePotatoes

    }

    private fun isContainsPotato(meal: Meal): Boolean {
        return meal.ingredients.ingredients.any { it.contains("potato", ignoreCase = true) }
    }
}