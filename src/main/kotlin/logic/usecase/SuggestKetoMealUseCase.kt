package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class SuggestKetoMealUseCase(private val mealsRepository: MealsRepository) {

    fun getKetoMeal(): Meal? {
        return mealsRepository.getAllMeals()
            .filter(::isKetoFriendly)
            .shuffled()
            .randomOrNull()
    }


    /**
     * keto meal diet requirements
     * - carb limitation < 10g per meal
     * - protein 20g-50g per meal
     * - fat content 30g+ per meal
     */
    private fun isKetoFriendly(meal: Meal): Boolean {
        return meal.nutrition.carbohydrates < 10 &&
                meal.nutrition.protein in 20.0..50.0 &&
                meal.nutrition.totalFat >= 30
    }
}