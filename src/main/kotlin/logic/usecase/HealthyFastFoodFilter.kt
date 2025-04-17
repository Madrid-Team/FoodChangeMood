package logic.usecase

import data.models.Meal
import logic.Repository.MealsFilter

class HealthyFastFoodFilter : MealsFilter {

    override fun getFilterMeals(meal: List<Meal>): List<Meal> {
        return meal.filter(::onlyHealthyMeal)
            .sortedBy(::getAverageNutrientsForMeal)
            .take(TOP_HEALTHY_MEAL)
    }


    private fun onlyHealthyMeal(meal: Meal) : Boolean {
        return meal.minutes <= MAX_PREPARED_TIME &&
                meal.nutrition.totalFat < MAX_TOTAL_FAT &&
                meal.nutrition.saturatedFat < MAX_SATURATED_FAT &&
                meal.nutrition.carbohydrates < MAX_CARBOHYDRATES
    }


    private fun getAverageNutrientsForMeal(meal: Meal) = meal.nutrition.totalFat + meal.nutrition.saturatedFat + meal.nutrition.carbohydrates


    companion object {
        const val MAX_PREPARED_TIME = 15
        const val MAX_TOTAL_FAT = 20.0
        const val MAX_SATURATED_FAT = 5.0
        const val MAX_CARBOHYDRATES = 50.0
        const val TOP_HEALTHY_MEAL = 15
    }
}