package logic.usecase

import data.csvHandler.Tags.UserMessages
import data.models.Meal
import data.utilities.MealsExceptions
import data.utilities.parseDateString
import logic.Repository.MealsRepository

class GetFoodByAddDateUseCase(private val mealRepository: MealsRepository) {


    operator fun invoke(date: String): List<Meal> {
        val parsedDate = parseDateString(date)
        val meals = mealRepository.getMealsByDate(date = parsedDate)
        if (meals.isEmpty()) {
            throw MealsExceptions.MealNotFoundException(UserMessages.MESSAGE_MEALS_FOUND_FOR_DATE.format(date))
        }
        return meals
    }
}
