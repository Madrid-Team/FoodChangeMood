package presentation.features

import logic.usecase.mealIngredientsGame.GetGameScoreUseCase
import logic.usecase.mealIngredientsGame.GetIngredientGameRandomMealUseCase
import logic.usecase.mealIngredientsGame.MakeGuessUseCase
import presentation.common.BaseUIController

class MealIngredientsGameUI(
    private val getRandomMealUseCase: GetIngredientGameRandomMealUseCase,
    private val makeGuessUseCase: MakeGuessUseCase,
    private val getGameScoreUseCase: GetGameScoreUseCase
) : BaseUIController {
    override val id: Int = 11
    override val message: String =
        "11- Ingredient Game ..\n" +
                "- you will get a meal name and three ingredient options.\n" +
                "- you can guess once .. A correct guess earns 1000 points , an incorrect guess ends the game.\n" +
                "- The game also ends after 15 correct answers."

    override fun start() {
        displayWelcomeMessage()
        playGame()
    }

    private fun displayWelcomeMessage() {
        println("\n===== Meal Ingredients Guessing Game =====")
        println("You will be asked to guess the correct ingredient for a meal")
        println("based only on its name.")
        println("You can only guess one time so make it count!")
        println("-".repeat(50))
        println("Let's get started!")
    }

    private fun playGame() {
        try {
            val randomMeal = getRandomMealUseCase()

            println("\n===== Your Challenge =====")
            println("The meal is: ${randomMeal.mealName}")
            println("\nPossible ingredients:")
            println("-".repeat(30))

            randomMeal.options.forEachIndexed { index, ingredient ->
                println("${index + 1}. $ingredient")
            }

            println("\nWhat is the correct ingredient? Enter the number of your choice (1-${randomMeal.options.size}):")

            val userInput = readlnOrNull() ?: ""

            if (userInput.isBlank()) {
                println("\n⚠ You didn't enter anything!")
            } else {
                try {
                    val userChoice = userInput.toInt()
                    if (userChoice < 1 || userChoice > randomMeal.options.size) {
                        println("\n⚠ Invalid number! Please choose between 1 and ${randomMeal.options.size}.")
                    } else {
                        val selectedIngredient = randomMeal.options[userChoice - 1]
                        val result =
                            makeGuessUseCase.invoke(guess = selectedIngredient, correctGuess = randomMeal.correctAnswer)
                        println("\n✓ $result")
                    }
                } catch (_: NumberFormatException) {
                    println("\n⚠ Please enter a valid number.")
                } catch (exception: MealsExceptions.GuessMealGameNotPassed) {
                    println("\n✗ ${exception.message}")
                    println("The correct answer was: ${randomMeal.correctAnswer}")
                } catch (exception: MealsExceptions.GuessMealGamePassed) {
                    println("\n🏆  ${exception.message}")
                } finally {
                    displayScore()
                }
            }

            askToPlayAgain()
        } catch (exception: Exception) {
            println("\n⚠ An unexpected error occurred: ${exception.message}")
        }
    }

    private fun displayScore() {
        println("\n===== Current Score =====")
        println("Your score: ${getGameScoreUseCase()}")
        println("-".repeat(30))
    }

    private fun askToPlayAgain() {
        println("\nWould you like to play again? (yes/no)")
        when (readlnOrNull()?.lowercase() ?: "") {
            "yes", "y" -> {
                println("\n")
                playGame()
            }

            else -> println("\nThank you for playing!")
        }
    }
}

