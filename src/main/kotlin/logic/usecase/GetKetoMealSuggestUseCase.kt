package logic.usecase

import data.csvHandler.Tags.Keto
import data.csvHandler.Tags.UserMessages
import data.models.Meal
import logic.Repository.MealsRepository

class GetKetoMealSuggestUseCase(mealsRepository: MealsRepository) {

    private val mealsFilter = mealsRepository.getAllMeals()
        .filter(::isKetoFriendly)
        .toMutableList()

    fun getKetoMeal(): Meal {
        if (mealsFilter.isEmpty()) {
            throw NoSuchElementException(UserMessages.MESSAGE_NO_MEAL_SUGGEST)
        }

        return mealsFilter
            .random()
            .also { mealsFilter.remove(it) }
    }


    /**
     * keto meal diet requirements
     * - carb < 10g per meal
     * - protein 20g-50g per meal
     * - fat 30g+ per meal
     */
    private fun isKetoFriendly(meal: Meal): Boolean {
        val nutritionValue = meal.nutrition.carbohydrates <  Keto.MAX_NUTRITION_CARBOHYDRATE &&
                meal.nutrition.protein in  Keto.MIN_NUTRITION_PROTEIN.. Keto.MAX_NUTRITION_PROTEIN &&
                meal.nutrition.totalFat >=  Keto.MIN_TOTAL_FAT

        val calories = meal.nutrition.calories
        val carbPercentage = (meal.nutrition.carbohydrates *  Keto.CARBS_PROTEIN_PER_GRAM) / calories * Keto.PERCENTAGE
        val proteinPercentage = (meal.nutrition.protein *  Keto.CARBS_PROTEIN_PER_GRAM) / calories *  Keto.PERCENTAGE
        val fatPercentage = (meal.nutrition.totalFat *  Keto.FAT_CALORIES_PER_GRAM) / calories *  Keto.PERCENTAGE

        val percentage = carbPercentage <  Keto.MAX_CARB_PERCENTAGE &&
                fatPercentage >  Keto.MIN_FAT_PERCENTAGE &&
                proteinPercentage in  Keto.MIN_NUTRITION_PROTEIN.. Keto.MAX_NUTRITION_PROTEIN

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