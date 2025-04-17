package logic.Repository

import data.models.Meal

interface MealsFilter {
    fun getFilterMeals(meal: List<Meal>): List<Meal>
}