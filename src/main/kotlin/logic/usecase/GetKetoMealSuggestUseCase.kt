package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetKetoMealSuggestUseCase(private val mealsRepository: MealsRepository) {

    private val mealsFilter = mealsRepository.getAllMeals()
        .filter(::isKetoFriendly)
        .toMutableList()


    fun getKetoMeal(): Meal? {
        val meal = mealsFilter
            .shuffled()
            .randomOrNull()
        if(meal != null) {
            mealsFilter.remove(meal)
        }
        return meal
    }




    /**
     * keto meal diet requirements
     * - carb limitation < 10g per meal
     * - protein 20g-50g per meal
     * - fat content 30g+ per meal
     */
    private fun isKetoFriendly(meal: Meal): Boolean {
        return meal.nutrition.carbohydrates < MAX_NUTRITION_CARBOHYDRATE &&
                meal.nutrition.protein in MIN_NUTRITION_PROTEIN..MAX_NUTRITION_PROTEIN &&
                meal.nutrition.totalFat >= MIN_TOTAL_FAT
    }

    companion object {
        const val MAX_NUTRITION_CARBOHYDRATE = 10.0
        const val MIN_NUTRITION_PROTEIN = 20.0
        const val MAX_NUTRITION_PROTEIN = 50.0
        const val MIN_TOTAL_FAT = 30.0
    }
}