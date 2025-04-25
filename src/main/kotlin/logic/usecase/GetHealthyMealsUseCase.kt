package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetHealthyMealsUseCase(private val mealsRepository: MealsRepository) {

    fun execute(count: Int): List<Meal> {
        return mealsRepository.getAllMeals()
            .filter(::isHealthyMeal)
            .sortedBy(::nutrientsSum)
            .take(count)
            .takeIf { it.isNotEmpty() }
            ?: throw NoSuchElementException("There is no healthy meals")
    }

    private fun isHealthyMeal(meal: Meal): Boolean {
        return meal.minutes <= MAX_PREPARED_TIME &&
                meal.nutrition.totalFat < MAX_TOTAL_FAT &&
                meal.nutrition.saturatedFat < MAX_SATURATED_FAT &&
                meal.nutrition.carbohydrates < MAX_CARBOHYDRATES
    }


    private fun nutrientsSum(meal: Meal) =
        meal.nutrition.totalFat + meal.nutrition.saturatedFat + meal.nutrition.carbohydrates

    companion object {
        const val MAX_PREPARED_TIME = 15
        const val MAX_TOTAL_FAT = 20.0
        const val MAX_SATURATED_FAT = 5.0
        const val MAX_CARBOHYDRATES = 50.0
    }
}