package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetMealsSuitableForGymUseCase(private val mealsRepository: MealsRepository) {

    fun getNameofGymMeals(calories: Double, protein: Double): List<String> {
        return mealsRepository.getAllMeals().filter { meal ->
            meal.nutrition.calories in (calories - 10)..(calories + 10) && meal.nutrition.protein in (protein - 10)..(protein + 10)
        }.map { meal ->
            meal.name
        }
    }

}