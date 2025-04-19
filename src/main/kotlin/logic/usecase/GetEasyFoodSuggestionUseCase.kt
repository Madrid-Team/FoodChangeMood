package logic.usecase

import data.models.Meal
import logic.MealsFilter
import logic.Repository.MealsRepository

class GetEasyFoodSuggestionUseCase(
    repository: MealsRepository,
) : MealsFilter {

    private val allMeals = repository.getAllMeals()

    override fun getFilterMeals(): List<Meal> {
        return allMeals.filter(::isEasyFood)
            .takeIf { it.isNotEmpty() }
            ?.take(TOP_TEN_SUGGEST)
            ?: throw NoSuchElementException("There is no easy food suggestion")
    }

    private fun isEasyFood(meal: Meal): Boolean {
        return meal.minutes <= MAX_PREPARED_TIME &&
                meal.steps.stepsCount <= MAX_STEPS_COUNT &&
                meal.ingredients.ingredientsCount <= MAX_INGREDIENTS_COUNT
    }

    companion object {
        const val TOP_TEN_SUGGEST = 10
        const val MAX_PREPARED_TIME = 30
        const val MAX_STEPS_COUNT = 6
        const val MAX_INGREDIENTS_COUNT = 5
    }

}