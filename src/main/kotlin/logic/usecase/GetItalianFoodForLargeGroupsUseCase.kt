package logic.usecase

import data.csvHandler.Tags.MealCategories.FOR_LARGE_GROUP
import data.models.Meal
import data.utilities.italian
import logic.Repository.MealsRepository

class GetItalianFoodForLargeGroupsUseCase(private val mealsRepository: MealsRepository) {

    fun getItalianFoodForLargeGroups(): List<Meal> {
        return mealsRepository.getAllMeals().filter { meal -> isItalian(meal) && isForLargeGroups(meal) }
    }

    private fun isForLargeGroups(meal: Meal): Boolean {
        return meal.tags.contains(FOR_LARGE_GROUP)
    }


    private fun isItalian(meal: Meal): Boolean {
        return meal.tags.contains(String.italian) ||
                meal.ingredients.ingredients.contains(String.italian) ||
                (meal.description?.contains(String.italian, ignoreCase = true) ?: false)
    }



}