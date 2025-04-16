package logic.usecase.mealIngredientsGame

import MealsExceptions.GuessMealGameNotPassed
import logic.Repository.MealsRepository

class MakeGuessUseCase(private val repository: MealsRepository) {


    operator fun invoke(guess: String, correctGuess: String): String {
        return if (guess == correctGuess) {
            repository.addCorrectGuessedMealName(guess)
        } else
            throw GuessMealGameNotPassed("ooh, you didn't guess the meal name")
    }
}