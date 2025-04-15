package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository
import logic.Repository.MealsSearch

class HealthyFastFoodManager(
    private val repository: MealsRepository,
    private val mealsSearch: MealsSearch
) {
    fun getTopHealthyFastFood(): List<Meal> {
        val allMeals = repository.getAllMeals()
        return mealsSearch.getSearchMeals(allMeals)
    }
}