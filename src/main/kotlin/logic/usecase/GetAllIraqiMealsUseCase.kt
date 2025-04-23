package logic.usecase

import data.models.Meal
import data.utilities.iraq
import data.utilities.iraqi
import logic.Repository.MealsRepository

class GetAllIraqiMealsUseCase(private val repository: MealsRepository) {
    fun getAllIraqiMeals(): List<Meal> {
        return repository.getAllMeals()
            .filter {
                it.tags.any { tag -> tag.contains(String.iraqi, ignoreCase = true) }
                        || (it.description?.contains(String.iraq, ignoreCase = true) ?: false)
            }
    }

}






