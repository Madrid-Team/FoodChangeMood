package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetSweetsWithNoEggsUseCase(private val mealsRepository: MealsRepository) {

    fun getfSweetFreeEggs(): Meal {
        return mealsRepository.getAllMeals().filter { meal ->
            meal.tags.contains("sweet") && !meal.ingredients.ingredients.contains("egg")
        }.random()
    }

}