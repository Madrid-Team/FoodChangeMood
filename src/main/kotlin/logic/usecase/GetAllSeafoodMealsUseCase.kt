package logic.usecase

import data.csvHandler.Tags.MealCategories.SEAFOOD
import data.models.SeafoodMeal
import logic.Repository.MealsRepository

class GetAllSeafoodMealsUseCase(
    private val repository: MealsRepository
) {
    fun getAllSeafoodMeals(): List<SeafoodMeal> {
        return repository.getAllMeals().filter { meal ->
            meal.tags.contains(SEAFOOD)
        }.sortedByDescending {
            it.nutrition.protein
        }.map { it.toSeafoodMeal() }
    }
}