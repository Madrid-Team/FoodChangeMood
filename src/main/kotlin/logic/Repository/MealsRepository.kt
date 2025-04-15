package logic.Repository

import data.models.Meal

interface MealsRepository {
    fun getAllMeals(): List<Meal>
}