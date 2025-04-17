package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetSweetsWithNoEggsUseCase(private val mealsRepository: MealsRepository) {

    fun getfSweetFreeEggs(): Meal {
        val sweetFreeEggsId = mutableListOf<Int>()

        val sweetFreeEgss = mealsRepository.getAllMeals().filter { meal ->
            meal.tags.contains("sweet") && !meal.ingredients.ingredients.contains("egg") && meal.id !in sweetFreeEggsId
        }.random()
        sweetFreeEgss.let { sweet -> sweetFreeEggsId.add(sweet.id) }

        return sweetFreeEgss
    }

}