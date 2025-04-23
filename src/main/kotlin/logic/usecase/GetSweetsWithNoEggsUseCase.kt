package logic.usecase

import data.csvHandler.Tags.MealCategories.SWEETS
import data.csvHandler.Tags.MealCategories.EGG
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
        return meal.tags.contains(SWEETS) && !meal.ingredients.ingredients.contains(EGG) && meal.id !in sweetsWithoutEggsIds
    }





}