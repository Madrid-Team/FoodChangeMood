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

}