package logic

import data.models.Meal

interface MealsFilter {
    fun getFilterMeals(): List<Meal>
}