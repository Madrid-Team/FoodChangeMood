package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetAllIraqiMealsUseCase(private val repository: MealsRepository) {
    fun getAllIraqiMeals():List<Meal>{
        return repository.getAllMeals()
            .filter { it.tags.any { it.contains("iraqi", ignoreCase = true) }
                    ||(it.description?.contains("iraq", ignoreCase = true)?:false ) }
    }
}






