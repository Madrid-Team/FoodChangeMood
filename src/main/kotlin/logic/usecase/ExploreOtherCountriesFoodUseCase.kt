package logic.usecase

import data.csvHandler.Tags.MealCategories.COUNTRY_MEAL_COUNT
import data.models.Meal
import logic.Repository.MealsRepository

class ExploreOtherCountriesFoodUseCase(
    private val mealsRepository: MealsRepository
) {
    fun getSearchedCountryMeals(countryName: String): List<Meal> {
        val lowerCaseCountryName = countryName.lowercase()
        return mealsRepository.getAllMeals()
            .filter { isMealRelatedToTheCountry(it, lowerCaseCountryName) }
            .shuffled()
            .take(COUNTRY_MEAL_COUNT)
    }

    private fun isMealRelatedToTheCountry(meal: Meal, lowerCountryName: String): Boolean {
        return meal.tags.any { it.lowercase().contains(lowerCountryName) }
                || meal.description?.contains(lowerCountryName) == true
                || meal.name.contains(lowerCountryName)
    }
}