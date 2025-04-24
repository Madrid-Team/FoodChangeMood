package logic.usecase

import data.csvHandler.Tags.MealCategories.POTATO
import data.csvHandler.Tags.MealCategories.RANDOM_MEALS_INCLUDE_POTATO
import data.models.Meal
import logic.Repository.MealsRepository

class ShowRandomMealsIncludePotatoesUseCase(
    private val mealsRepository: MealsRepository
) {
    fun showRandomMealsIncludePotatoes(): List<Meal> {
        val filteredMealsIncludePotatoes = mealsRepository.getAllMeals()
            .filter(::isContainsPotato)
            .shuffled()
            .take(RANDOM_MEALS_INCLUDE_POTATO)
        if (filteredMealsIncludePotatoes.isEmpty()) throw NoSuchElementException("Can't found meals include potatoes")
        return filteredMealsIncludePotatoes
    }

    private fun isContainsPotato(meal: Meal): Boolean {
        return meal.ingredients.ingredients.any { it.contains(POTATO, ignoreCase = true) }
    }
}