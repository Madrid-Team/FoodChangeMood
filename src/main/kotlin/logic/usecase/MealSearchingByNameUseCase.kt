package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository
import utils.kmpSearch

class MealSearchingByNameUseCase(
    private val mealsRepository: MealsRepository
) {
    fun searchAboutMealByName(mealName: String): List<Meal> {
        val filtered = mealsRepository.getAllMeals().filter { meal ->
            kmpSearch(searchedFullText = meal.name, searchedPattern = mealName)
        }
        if (filtered.isEmpty()) throw NoSuchElementException("No meal found with name $mealName")
        return filtered
    }
}