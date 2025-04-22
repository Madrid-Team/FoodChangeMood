package presentation.features

import logic.usecase.StartGuessGameUseCase
import presentation.common.BaseUIController

class GuessGameConsoleUi(
    private val startGuessGameUseCase: StartGuessGameUseCase,
) : BaseUIController {

    override val id: Int = 5
    override val message: String = "" +
            "5- Guess game .. " +
            "you will show the random meal name and you will guess it's preparation time.\n" +
            "- you have 3 attempts .. After each attempt, The guessed time is correct, too low, or too high.\n" +
            "- If all attempts are incorrect, you will see the correct time."

    override fun start() {
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