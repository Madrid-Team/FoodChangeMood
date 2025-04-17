package logic.usecase

import logic.Repository.MealsRepository

class StartGuessGameUseCase(private val mealsRepository: MealsRepository) {

    fun startGuessGame(guess: Int, correctTime: Int): String {
        return when {
            guess == correctTime -> "Correct"
            guess < correctTime -> "Too low"
            else -> "Too high"
        }
    }

    fun getRandomMeal(): Pair<String, Int> {
        return mealsRepository.getAllMeals()
            .random()
            .let { it.name to it.minutes }
    }
}