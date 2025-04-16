package logic.usecase

import logic.Repository.MealsRepository

class StartGuessGameUseCase(private val mealsRepository: MealsRepository) {

    fun startGuessGame(guess: () -> Int) {
//        val meal = mealsRepository.getAllMeals().random()
        println("Guess the preparation time for: elhady's favourite meals ")
        println("You have 3 attempts")

        val correctTime = 20

        (1..3).forEach { attempt ->
            println("Attempt $attempt: Try to guess in minutes: ")
            when {
                guess() == correctTime -> {
                    println("Correct $correctTime m")
                    return
                }

                guess() < correctTime -> println("Too low")
                else -> println("Too high")
            }
        }
        println("The correct preparation time for elhady's favourite meals is 50 m.")
    }
}