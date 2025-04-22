package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetAllIraqiMealsUseCase(private val repository: MealsRepository) {
    fun getAllIraqiMeals(): List<Meal> {
        return repository.getAllMeals()
            .filter {
                it.tags.any { it.contains(IRAQI, ignoreCase = true) }
                        || (it.description?.contains(IRAQ, ignoreCase = true) ?: false)
            }
    }

    companion object {
        const val IRAQI = "iraqi"
        const val IRAQ = "iraq"
    }
}






