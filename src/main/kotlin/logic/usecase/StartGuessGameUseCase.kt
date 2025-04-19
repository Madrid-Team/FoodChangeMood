package logic.usecase

import logic.Repository.MealsRepository

class StartGuessGameUseCase(private val mealsRepository: MealsRepository) {

    fun startGuessGame(): Pair<String, Int> {
        return mealsRepository.getAllMeals()
            .random()
            .let { it.name to it.minutes }
    }
}