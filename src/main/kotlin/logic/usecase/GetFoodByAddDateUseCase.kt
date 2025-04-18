package logic.usecase

import data.models.Meal
import data.utilities.MealsExceptions
import data.utilities.parseDateString
import logic.Repository.MealsRepository

class GetFoodByAddDateUseCase(private val mealRepository: MealsRepository) {


    operator fun invoke(date: String): List<Meal> {
        val parsedDate = parseDateString(date)
        val meals = mealRepository.getMealsByDate(date = parsedDate)
        if (meals.isEmpty()) {
            throw MealsExceptions.MealNotFoundException("No meals found for date: $date")
        }
        return meals
    }
}
