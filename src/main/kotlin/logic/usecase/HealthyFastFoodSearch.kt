package logic.usecase

import data.models.Meal
import logic.Repository.MealsSearch

class HealthyFastFoodSearch : MealsSearch {

    override fun getSearchMeals(meal: List<Meal>): List<Meal> {
        val mealsFiltered = meal.filter(::onlyHealthyMeal)

        if (mealsFiltered.isEmpty()) {
            throw NotFoundHealthyFastFoodException("Not Found Meals Healthy Fast Food")
        }

        return mealsFiltered
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

class NotFoundHealthyFastFoodException(message: String) : Exception(message)
