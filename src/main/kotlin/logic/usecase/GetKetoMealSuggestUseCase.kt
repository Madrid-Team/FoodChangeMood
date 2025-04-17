package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetKetoMealSuggestUseCase(mealsRepository: MealsRepository) {

    private val mealsFilter = mealsRepository.getAllMeals()
        .filter(::isKetoFriendly)
        .toMutableList()


    fun getKetoMeal(): Meal {
        if (mealsFilter.isEmpty()) {
            throw NoSuchElementException("There is no Meal Suggest!")
        }

        return mealsFilter
            .random()
            .also { mealsFilter.remove(it) }
    }




    /**
     * keto meal diet requirements
     * - carb limitation < 10g per meal
     * - protein 20g-50g per meal
     * - fat content 30g+ per meal
     */
    private fun isKetoFriendly(meal: Meal): Boolean {
        val mealNutrition = meal.nutrition
        return mealNutrition.carbohydrates < MAX_NUTRITION_CARBOHYDRATE &&
                mealNutrition.protein in MIN_NUTRITION_PROTEIN..MAX_NUTRITION_PROTEIN &&
                mealNutrition.totalFat >= MIN_TOTAL_FAT
    }

    companion object {
        const val MAX_NUTRITION_CARBOHYDRATE = 10.0
        const val MIN_NUTRITION_PROTEIN = 20.0
        const val MAX_NUTRITION_PROTEIN = 50.0
        const val MIN_TOTAL_FAT = 30.0
    }
}