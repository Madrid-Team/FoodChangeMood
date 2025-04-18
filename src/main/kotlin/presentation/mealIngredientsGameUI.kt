package presentation

import logic.usecase.mealIngredientsGame.GetGameScoreUseCase
import logic.usecase.mealIngredientsGame.GetIngredientGameRandomMealUseCase
import logic.usecase.mealIngredientsGame.MakeGuessUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MealIngredientsGameUI : KoinComponent {
    private val getRandomMealUseCase: GetIngredientGameRandomMealUseCase by inject()
    private val makeGuessUseCase: MakeGuessUseCase by inject()
    private val getGameScoreUseCase: GetGameScoreUseCase by inject()
    
    fun start() {
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
            
            println("\nWhat is the correct ingredient? Enter the name of the ingredient:")
            
            val userGuess = readlnOrNull() ?: ""
            
            if (userGuess.isBlank()) {
                println("\n⚠ You didn't enter anything!")
            } else {
                try {
                    val result = makeGuessUseCase.invoke(guess = userGuess, correctGuess = randomMeal.correctAnswer)
                    println("\n✓ $result")
                } catch (exception: MealsExceptions.GuessMealGameNotPassed) {
                    println("\n✗ ${exception.message}")
                    println("The correct answer was: ${randomMeal.correctAnswer}")
                } catch (exception: MealsExceptions.GuessMealGamePassed) {
                    println("\n🏆 ${exception.message}")
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

