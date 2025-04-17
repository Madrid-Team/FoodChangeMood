package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetAllMealsUseCase(private val mealsRepository: MealsRepository) {
    fun getAllMeals(): List<Meal> {
        return mealsRepository.getAllMeals()
    }
}