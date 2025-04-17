package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class ExploreOtherCountriesFoodUseCase(
    private val mealsRepository: MealsRepository
) {
    fun getRandomMeals(countryName: String): List<Meal> {
        val lowerCountryName = countryName.lowercase()
        return mealsRepository.getAllMeals()
            .filter { meal ->
                meal.tags.any { it.lowercase().contains(lowerCountryName) }
                        || meal.description?.contains(lowerCountryName) == true
                        || meal.name.contains(lowerCountryName)
            }
            .shuffled()
            .take(20)
    }
}