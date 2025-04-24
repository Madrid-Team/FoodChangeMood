package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class SuggestEasyMealUseCase(
    private val mealsRepository: MealsRepository,
) {

    fun execute(count: Int): List<Meal> {
        if (mealsRepository.getAllMeals().isEmpty()) {
            throw Exception("Meal list is empty")
        }
        return mealsRepository.getAllMeals()
            .filter(::isEasyFood)
            .take(count)
            .takeIf { it.isNotEmpty() }
            ?: throw NoSuchElementException("There is no easy food suggestion")
    }

    private fun isEasyFood(meal: Meal): Boolean {
        return meal.minutes <= MAX_PREPARED_TIME &&
                meal.steps.stepsCount <= MAX_STEPS_COUNT &&
                meal.ingredients.ingredientsCount <= MAX_INGREDIENTS_COUNT
    }

    companion object {
        const val MAX_PREPARED_TIME = 30
        const val MAX_STEPS_COUNT = 6
        const val MAX_INGREDIENTS_COUNT = 5
    }
}