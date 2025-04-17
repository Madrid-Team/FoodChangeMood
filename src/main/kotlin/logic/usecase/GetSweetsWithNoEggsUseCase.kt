package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetSweetsWithNoEggsUseCase(private val mealsRepository: MealsRepository) {

    fun getfSweetFreeEggs(): Meal {
        val sweetFreeEggsId = mutableListOf<Int>()
        var sweetFreeEggsCash = emptyList<Meal>()
        var isFirstCall = true

        if (isFirstCall) {
            sweetFreeEggsCash = mealsRepository.getAllMeals().filter { meal ->
                meal.tags.contains("sweet") && !meal.ingredients.ingredients.contains("egg")
            }
            isFirstCall = false
        }

        val remainningSweets = sweetFreeEggsCash.filter { it.id !in sweetFreeEggsId }

        val nextMeal = remainningSweets.random()
        nextMeal.let { sweetFreeEggsId.add(nextMeal.id) }

        return nextMeal
    }

}