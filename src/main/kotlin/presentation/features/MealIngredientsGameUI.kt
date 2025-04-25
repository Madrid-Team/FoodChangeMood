package presentation.features

import data.utilities.MealsExceptions
import logic.usecase.mealIngredientsGame.GetGameScoreUseCase
import logic.usecase.mealIngredientsGame.GetIngredientGameRandomMealUseCase
import logic.usecase.mealIngredientsGame.MakeGuessUseCase
import presentation.common.BaseUIController
import presentation.common.Reader
import presentation.common.Viewer

class MealIngredientsGameUI(
    private val getRandomMealUseCase: GetIngredientGameRandomMealUseCase,
    private val makeGuessUseCase: MakeGuessUseCase,
    private val getGameScoreUseCase: GetGameScoreUseCase,
    private val reader: Reader,
    private val viewer: Viewer
) : BaseUIController {
    override val id: Int = 11
    override val message: String =
        "$id- Ingredient Game ..\n" +
                "- you will get a meal name and three ingredient options.\n" +
                "- you can guess once .. A correct guess earns 1000 points , an incorrect guess ends the game.\n" +
                "- The game also ends after 15 correct answers."

    override fun start() {
        displayWelcomeMessage()
        playGame()
    }

    private fun displayWelcomeMessage() {
        viewer.show("\n===== Meal Ingredients Guessing Game =====")
        viewer.show("You will be asked to guess the correct ingredient for a meal")
        viewer.show("based only on its name.")
        viewer.show("You can only guess one time so make it count!")
        viewer.show("-".repeat(50))
        viewer.show("Let's get started!")
    }

    private fun playGame() {
        try {
            val randomMeal = getRandomMealUseCase()

            viewer.show("\n===== Your Challenge =====")
            viewer.show("The meal is: ${randomMeal.meal.name}")
            viewer.show("\nPossible ingredients:")
            viewer.show("-".repeat(30))

            randomMeal.options.forEachIndexed { index, ingredient ->
                viewer.show("${index + 1}. $ingredient")
            }

            viewer.show("\nWhat is the correct ingredient? Enter the number of your choice (1-${randomMeal.options.size}):")

            val userInput = reader.getUserInput() ?: ""

            if (userInput.isBlank()) {
                viewer.show("\n⚠ You didn't enter anything!")
            } else {
                try {
                    val userChoice = userInput.toInt()
                    if (userChoice < 1 || userChoice > randomMeal.options.size) {
                        viewer.show("\n⚠ Invalid number! Please choose between 1 and ${randomMeal.options.size}.")
                    } else {
                        val selectedIngredient = randomMeal.options[userChoice - 1]
                        val result =
                            makeGuessUseCase.invoke(guess = selectedIngredient, correctGuess = randomMeal.correctAnswer)
                        viewer.show("\n✓ $result")
                    }
                } catch (_: NumberFormatException) {
                    viewer.show("\n⚠ Please enter a valid number.")
                } catch (exception: MealsExceptions.GuessMealGameNotPassed) {
                    viewer.show("\n✗ ${exception.message}")
                    viewer.show("The correct answer was: ${randomMeal.correctAnswer}")
                } catch (exception: MealsExceptions.GuessMealGamePassed) {
                    viewer.show("\n🏆  ${exception.message}")
                } finally {
                    displayScore()
                }
            }

            askToPlayAgain()
        } catch (exception: Exception) {
            viewer.show("\n⚠ An unexpected error occurred: ${exception.message}")
        }
    }

    private fun displayScore() {
        viewer.show("\n===== Current Score =====")
        viewer.show("Your score: ${getGameScoreUseCase()}")
        viewer.show("-".repeat(30))
    }

    private fun askToPlayAgain() {
        viewer.show("\nWould you like to play again? (yes/no)")
        when (reader.getUserInput()?.lowercase() ?: "") {
            "yes", "y" -> {
                viewer.show("\n")
                playGame()
            }

            else -> viewer.show("\nThank you for playing!")
        }
    }
}

