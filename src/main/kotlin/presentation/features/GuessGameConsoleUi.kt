package presentation.features

import logic.usecase.StartGuessGameUseCase
import presentation.common.BaseUIController
import presentation.common.Viewer

class GuessGameConsoleUi(
    private val startGuessGameUseCase: StartGuessGameUseCase,
    private val viewer: Viewer
) : BaseUIController {

    override val id: Int = 5
    override val message: String = "" +
            "$id- Guess game .. " +
            "you will show the random meal name and you will guess it's preparation time.\n" +
            "- you have 3 attempts .. After each attempt, The guessed time is correct, too low, or too high.\n" +
            "- If all attempts are incorrect, you will see the correct time."

    override fun start() {
        val (mealName, correctTime) = startGuessGameUseCase.startGuessGame()
        viewer.show("Guess the preparation time for: $mealName")
        viewer.show("You have 3 attempts.")

        (1..3).forEach { attempt ->
            viewer.show("Attempt ${attempt}: Enter your guess in minutes: ")
            val guess = readlnOrNull()?.toIntOrNull() ?: 0

            when {
                guess == correctTime -> {
                    viewer.show("Correct! The preparation time is $correctTime minutes.")
                    return
                }

                guess < correctTime -> viewer.show("Too low! Try a higher number.")
                else -> viewer.show("Too high! Try a lower number.")
            }
        }

        viewer.show("Sorry, you've used all your attempts.")
        viewer.show("The correct preparation time for $mealName is $correctTime minutes.")
    }
}