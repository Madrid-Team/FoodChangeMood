package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetKetoMealSuggestUseCase(private val mealsRepository: MealsRepository) {

    fun getKetoMeal(): Meal? {
        return mealsRepository.getAllMeals()
            .filter(::isKetoFriendly)
            .randomOrNull()
    }

    /**
     * keto meal diet requirements
     * - carb < 10g per meal
     * - protein 20g-50g per meal
     * - fat 30g+ per meal
     */
    private fun isKetoFriendly(meal: Meal): Boolean {
        val nutritionValue = meal.nutrition.carbohydrates < MAX_NUTRITION_CARBOHYDRATE &&
                meal.nutrition.protein in MIN_NUTRITION_PROTEIN..MAX_NUTRITION_PROTEIN &&
                meal.nutrition.totalFat >= MIN_TOTAL_FAT

        val calories = meal.nutrition.calories
        val carbPercentage = (meal.nutrition.carbohydrates * CARBS_PROTEIN_PER_GRAM) / calories * PERCENTAGE
        val proteinPercentage = (meal.nutrition.protein * CARBS_PROTEIN_PER_GRAM) / calories * PERCENTAGE
        val fatPercentage = (meal.nutrition.totalFat * FAT_CALORIES_PER_GRAM) / calories * PERCENTAGE

        val percentage = carbPercentage < MAX_CARB_PERCENTAGE &&
                fatPercentage > MIN_FAT_PERCENTAGE &&
                proteinPercentage in MIN_NUTRITION_PROTEIN..MAX_NUTRITION_PROTEIN

        return nutritionValue && percentage

    }

    companion object {
        const val PERCENTAGE = 100

        // nutrition value
        const val MAX_NUTRITION_CARBOHYDRATE = 10.0
        const val MIN_NUTRITION_PROTEIN = 20.0
        const val MAX_NUTRITION_PROTEIN = 50.0
        const val MIN_TOTAL_FAT = 30.0

        // Keto Percentage
        const val MAX_CARB_PERCENTAGE = 5
        const val MIN_FAT_PERCENTAGE = 70

        // calories per gram
        const val CARBS_PROTEIN_PER_GRAM = 4
        const val FAT_CALORIES_PER_GRAM = 9
    }
}