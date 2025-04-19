package presentation

import logic.usecase.StartGuessGameUseCase

class GuessGameConsoleUi(private val startGuessGameUseCase: StartGuessGameUseCase) {

    fun startGame() {
        val (mealName, correctTime) = startGuessGameUseCase.startGuessGame()
        println("Guess the preparation time for: $mealName")
        println("You have 3 attempts.")

        (1..3).forEach { attempt ->
            print("Attempt ${attempt}: Enter your guess in minutes: ")
            val guess = readlnOrNull()?.toIntOrNull() ?: 0

            when {
                guess == correctTime -> {
                    println("Correct! The preparation time is $correctTime minutes.")
                    return
                }
                guess < correctTime -> println("Too low! Try a higher number.")
                else -> println("Too high! Try a lower number.")
            }
        }

        println("Sorry, you've used all your attempts.")
        println("The correct preparation time for $mealName is $correctTime minutes.")
    }
}