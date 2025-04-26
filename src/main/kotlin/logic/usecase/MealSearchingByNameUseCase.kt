package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository
import logic.SearchAlgorithm

class MealSearchingByNameUseCase(
    private val mealsRepository: MealsRepository,
    private val kmpSearchAlgorithm: SearchAlgorithm
) {
    fun searchAboutMealByName(mealName: String): List<Meal> {
        val filtered = mealsRepository.getAllMeals().filter { meal ->
            kmpSearchAlgorithm.search(fullText = meal.name, pattern = mealName)
        }
        if (filtered.isEmpty()) throw NoSuchElementException("No meal found with name $mealName")
        return filtered
    }
}