package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetSweetsWithNoEggsUseCase(private val mealsRepository: MealsRepository) {

    fun getOneSweetWithNoEggs(): Meal {
        val sweetsWithNoeEggsIds = mutableListOf<Int>()

        val oneSweetWithNoEggs = mealsRepository.getAllMeals()
            .filter { meal -> isSweetWithoutEggs(meal, sweetsWithNoeEggsIds) }
            .takeIf { it.isNotEmpty() }
            ?.random() ?: throw NoSuchElementException("No sweet without eggs exist")

        oneSweetWithNoEggs.let { sweet -> sweetsWithNoeEggsIds.add(sweet.id) }
        return oneSweetWithNoEggs
    }

    private fun isSweetWithoutEggs(meal: Meal, sweetsWithoutEggsIds: List<Int>): Boolean {
        return meal.tags.any { it.contains("sweet", ignoreCase = true) } &&
                !meal.ingredients.ingredients.any { it.contains("egg", ignoreCase = true) } &&
                meal.id !in sweetsWithoutEggsIds
    }

}