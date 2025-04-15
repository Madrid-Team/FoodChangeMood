package logic.usecase

import logic.Repository.MealsRepository

class StartGuessGameUseCase(private val mealsRepository: MealsRepository) {
    fun startGuessGame(){}
}