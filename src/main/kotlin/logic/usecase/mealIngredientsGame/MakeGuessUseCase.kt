package logic.usecase.mealIngredientsGame

import data.utilities.MealsExceptions.GuessMealGameNotPassed
import data.utilities.MealsExceptions.GuessMealGamePassed
import logic.Repository.MealsRepository

class MakeGuessUseCase(private val repository: MealsRepository) {

    operator fun invoke(guess: String, correctGuess: String): String {
        return if (guess == correctGuess) {
            repository.addCorrectGuessedMealName(guess)
            val correctGuessedMeals = repository.getCorrectGuessedMealsNames()
            if (correctGuessedMeals.size < 15) {
                repository.clearCorrectGuessedMealsNames()
                "that's correct! You have guessed ${correctGuessedMeals.size} meals correctly!"
            } else {
                throw GuessMealGamePassed("You have guessed all the meals correctly!")
            }
        } else
            throw GuessMealGameNotPassed("ooh, you didn't guess the meal name")
    }
}