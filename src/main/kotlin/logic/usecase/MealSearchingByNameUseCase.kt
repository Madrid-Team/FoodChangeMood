package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository
import utils.kmpSearch

class MealSearchingByNameUseCase(
    private val mealsRepository: MealsRepository
) {
    fun searchAboutMealByName(mealName: String): List<Meal> {
        return mealsRepository.getAllMeals().filter { meal ->
            kmpSearch(searchedFullText = meal.name, searchedPattern = mealName)
        }
    }
}