package logic.usecase

import data.models.Meal
import logic.Repository.MealsFilter
import logic.Repository.MealsRepository

class GetTenEasyFoodSuggestionUseCase(
    private val repository: MealsRepository,
    private val mealsSearch: MealsFilter
) {
    fun getTenEasyFoodSuggestion(): List<Meal> {
        val allMeals = repository.getAllMeals()
        return mealsSearch.getFilterMeals(allMeals)
    }
}