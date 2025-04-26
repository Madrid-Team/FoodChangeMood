package logic.usecase

import data.csvHandler.Tags.HealthyMealCriteria.MAX_CARBOHYDRATES
import data.csvHandler.Tags.HealthyMealCriteria.MAX_PREPARED_TIME_FOR_HEALTHY_FOOD
import data.csvHandler.Tags.HealthyMealCriteria.MAX_SATURATED_FAT
import data.csvHandler.Tags.HealthyMealCriteria.MAX_TOTAL_FAT
import data.csvHandler.Tags.HealthyMealCriteria.TOP_HEALTHY_MEAL
import data.csvHandler.Tags.UserMessages
import data.models.Meal
import logic.MealsFilter
import logic.Repository.MealsRepository

class GetHealthyFoodUseCase(
    repository: MealsRepository,
) : MealsFilter {

    private val allMeals = repository.getAllMeals()

    override fun getFilterMeals(): List<Meal> {
        return allMeals.filter(::isHealthyMeal)
            .takeIf { it.isNotEmpty() }
            ?.sortedBy(::nutrientsSum)
            ?.take(TOP_HEALTHY_MEAL) ?: throw NoSuchElementException(UserMessages.MESSAGE_NO_HEALTHY_FAST_FOOD)
    }

    private fun isHealthyMeal(meal: Meal): Boolean {
        val mealNutrition = meal.nutrition
        return meal.minutes <= MAX_PREPARED_TIME_FOR_HEALTHY_FOOD &&
                mealNutrition.totalFat < MAX_TOTAL_FAT &&
                mealNutrition.saturatedFat < MAX_SATURATED_FAT &&
                mealNutrition.carbohydrates < MAX_CARBOHYDRATES
    }



    private fun nutrientsSum(meal: Meal) =
        meal.nutrition.totalFat + meal.nutrition.saturatedFat + meal.nutrition.carbohydrates


}