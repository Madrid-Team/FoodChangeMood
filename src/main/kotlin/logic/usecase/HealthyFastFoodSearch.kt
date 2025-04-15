package logic.usecase

import data.models.Meal
import logic.Repository.MealsSearch

class HealthyFastFoodSearch : MealsSearch {

    override fun getSearchMeals(meal: List<Meal>): List<Meal> {

        return meal.filter { meals ->
            meals.minutes <= MAX_PREPARED_TIME &&
            meals.nutrition.totalFat < MAX_TOTAL_FAT &&
            meals.nutrition.saturatedFat < MAX_SATURATED_FAT &&
            meals.nutrition.carbohydrates < MAX_CARBOHYDRATES
        }.sortedBy { meals ->
            meals.nutrition.totalFat + meals.nutrition.saturatedFat + meals.nutrition.carbohydrates
        }.take(20)
    }



    companion object {
        const val MAX_PREPARED_TIME = 15
        const val MAX_TOTAL_FAT = 20.0
        const val MAX_SATURATED_FAT = 5.0
        const val MAX_CARBOHYDRATES = 50.0
    }
}