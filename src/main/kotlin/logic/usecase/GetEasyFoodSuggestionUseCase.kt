package logic.usecase

import data.csvHandler.Tags.MealFilters.MAX_INGREDIENTS_COUNT
import data.csvHandler.Tags.MealFilters.MAX_PREPARED_TIME_FOR_EASY_FOOD
import data.csvHandler.Tags.MealFilters.MAX_STEPS_COUNT
import data.csvHandler.Tags.MealFilters.TOP_TEN_SUGGEST
import data.csvHandler.Tags.UserMessages
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
            ?: throw NoSuchElementException(UserMessages.MESSAGE_NO_EASY_MEAL_SUGGEST)
    }

    private fun isEasyFood(meal: Meal): Boolean {
        return meal.minutes <= MAX_PREPARED_TIME_FOR_EASY_FOOD &&
                meal.steps.stepsCount <= MAX_STEPS_COUNT &&
                meal.ingredients.ingredientsCount <= MAX_INGREDIENTS_COUNT
    }

}