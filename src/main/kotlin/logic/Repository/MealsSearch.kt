package logic.Repository

import data.models.Meal

interface MealsSearch {
    fun getSearchMeals(meal: List<Meal>): List<Meal>
}