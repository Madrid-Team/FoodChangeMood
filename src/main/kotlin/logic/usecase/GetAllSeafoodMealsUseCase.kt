package logic.usecase

import data.models.SeafoodMeal
import logic.repository.MealsRepository

class GetAllSeafoodMealsUseCase(
    private val repository: MealsRepository
) {
    fun getAllSeafoodMeals(): List<SeafoodMeal> {
        return repository.getAllMeals().filter { meal ->
            meal.tags.contains("seafood")
        }.sortedByDescending {
            it.nutrition.protein
        }.map { it.toSeafoodMeal() }
    }
}