package logic.Repository

import data.models.Meal
import java.util.Date

interface MealsRepository {
    fun getAllMeals(): List<Meal>

    fun addCorrectGuessedMealName(mealName: String)

    fun getCorrectGuessedMealsNames(): List<String>
    fun clearCorrectGuessedMealsNames()
    fun getMealsByDate(date: Date): List<Meal>
    fun getMealByIdFromSelectedDate(id: Int): Meal?
}