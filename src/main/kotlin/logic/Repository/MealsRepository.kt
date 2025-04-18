package logic.Repository

import data.models.Meal

interface MealsRepository {
    fun getAllMeals(): List<Meal>

    fun addCorrectGuessedMealName(mealName: String)

    fun getCorrectGuessedMealsNames(): List<String>
    fun clearCorrectGuessedMealsNames()
}