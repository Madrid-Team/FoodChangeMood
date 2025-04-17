package logic.usecase

import data.models.Meal
import logic.Repository.MealsFilter

class EasyFoodSuggestionFilter: MealsFilter {
    override fun getFilterMeals(meal: List<Meal>): List<Meal> {
        return meal.filter(::onlyEasyFoodMeal)
            .take(TOP_TEN_SUGGEST)
    }

    private fun onlyEasyFoodMeal(meal: Meal): Boolean{
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