package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class ExploreOtherCountriesFoodUseCase(
    private val mealsRepository: MealsRepository
) {
    fun getSearchedCountryMeals(countryName: String): List<Meal> {
        val lowerCaseCountryName = countryName.lowercase()
        val filteredMeals = mealsRepository.getAllMeals()
            .filter { isMealRelatedToTheCountry(it, lowerCaseCountryName) }
            .shuffled()
            .take(20)
        if (shouldThrowException(
                filteredMeals,
                lowerCaseCountryName
            )
        ) throw NoSuchElementException("Can't find meals for this country")
        return filteredMeals
    }

    private fun shouldThrowException(filteredMeals: List<Meal>, countryName: String): Boolean {
        return filteredMeals.isEmpty() ||
                countryName.isBlank() ||
                countryName.isEmpty()
    }

    private fun isMealRelatedToTheCountry(meal: Meal, lowerCountryName: String): Boolean {
        return meal.tags.any { it.lowercase().contains(lowerCountryName) }
                || meal.description?.contains(lowerCountryName) == true
                || meal.name.contains(lowerCountryName)
    }
}