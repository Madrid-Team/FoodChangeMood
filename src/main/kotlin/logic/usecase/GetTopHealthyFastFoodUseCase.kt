package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository
import logic.Repository.MealsFilter

class GetTopHealthyFastFoodUseCase(
    private val repository: MealsRepository,
    private val mealsFilter: MealsFilter
) {
    fun getTopHealthyFastFood(): List<Meal> {
        val allMeals = repository.getAllMeals()
        return mealsFilter.getFilterMeals(allMeals)
    }
}