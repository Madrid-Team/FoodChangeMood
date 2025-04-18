package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetSweetsWithNoEggsUseCase(private val mealsRepository: MealsRepository) {

    fun getOneSweetWithNoEggs(): Meal {
        val sweetsWithNoeEggsIds = mutableListOf<Int>()

        val oneSweetWithNoEggs = mealsRepository.getAllMeals().filter { meal ->
            isSweetWithoutEggs(meal, sweetsWithNoeEggsIds)
        }.random()
        oneSweetWithNoEggs.let { sweet -> sweetsWithNoeEggsIds.add(sweet.id) }

        return oneSweetWithNoEggs
    }

    private fun isSweetWithoutEggs(meal : Meal, sweetsWithoutEggsIds: List<Int> ): Boolean{
        return meal.tags.contains("sweet") && !meal.ingredients.ingredients.contains("egg") && meal.id !in sweetsWithoutEggsIds
    }





}