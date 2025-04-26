package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class SuggestNewKetoMealUseCase(private val mealsRepository: MealsRepository) {

    fun execute(alreadySuggestedIds: Set<Int>): Meal {
        return mealsRepository.getAllMeals()
            .filter { isKetoFriendly(it) && it.id !in alreadySuggestedIds }
            .takeIf { it.isNotEmpty() }
            ?.first()
            ?: throw NoSuchElementException("There is no Meal Suggest!")
    }


    /**
     * keto meal diet requirements
     * - carb < 10g per meal
     * - protein 20g-50g per meal
     * - fat 30g+ per meal
     * - sugar >= 5
     */
    private fun isKetoFriendly(meal: Meal): Boolean {
        return meal.nutrition.carbohydrates < MAX_NUTRITION_CARBOHYDRATE &&
                meal.nutrition.protein in MIN_NUTRITION_PROTEIN..MAX_NUTRITION_PROTEIN &&
                meal.nutrition.totalFat >= MIN_TOTAL_FAT &&
                meal.nutrition.sugar >= MAX_SUGAR
    }

    companion object {
        const val MAX_NUTRITION_CARBOHYDRATE = 10.0
        const val MIN_NUTRITION_PROTEIN = 20.0
        const val MAX_NUTRITION_PROTEIN = 50.0
        const val MAX_SUGAR = 5.0
        const val MIN_TOTAL_FAT = 30.0
    }
}