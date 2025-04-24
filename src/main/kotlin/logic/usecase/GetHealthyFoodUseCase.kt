package logic.usecase

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
            ?.take(TOP_HEALTHY_MEAL) ?: throw NoSuchElementException("There is no healthy fast food")
    }

    private fun isHealthyMeal(meal: Meal): Boolean {
        val mealNutrition = meal.nutrition
        return meal.minutes <= MAX_PREPARED_TIME &&
                mealNutrition.totalFat < MAX_TOTAL_FAT &&
                mealNutrition.saturatedFat < MAX_SATURATED_FAT &&
                mealNutrition.carbohydrates < MAX_CARBOHYDRATES
    }


    private fun nutrientsSum(meal: Meal) =
        meal.nutrition.totalFat + meal.nutrition.saturatedFat + meal.nutrition.carbohydrates

    companion object {
        const val MAX_PREPARED_TIME = 15
        const val MAX_TOTAL_FAT = 20.0
        const val MAX_SATURATED_FAT = 5.0
        const val MAX_CARBOHYDRATES = 50.0
        const val TOP_HEALTHY_MEAL = 15
    }
}