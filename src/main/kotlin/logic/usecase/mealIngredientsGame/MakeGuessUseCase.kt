package logic.usecase.mealIngredientsGame

import data.csvHandler.Tags
import data.csvHandler.Tags.GameScore.MAX_CORRECT_GUESSES
import logic.Repository.MealsRepository

class MakeGuessUseCase(private val repository: MealsRepository) {

    operator fun invoke(guess: String, correctGuess: String): String {
        return if (guess == correctGuess) {
            repository.addCorrectGuessedMealName(guess)
            val correctGuessedMeals = repository.getCorrectGuessedMealsNames()
            if (correctGuessedMeals.size < MAX_CORRECT_GUESSES) {
                repository.clearCorrectGuessedMealsNames()
                Tags.UserMessages.MESSAGE_CORRECT_GUESS.format(correctGuessedMeals.size)
            } else {
                throw MealsExceptions.GuessMealGamePassed(Tags.UserMessages.MESSAGE_ALL_GUESSES_PASSED)
            }
        } else
            throw MealsExceptions.GuessMealGameNotPassed(Tags.UserMessages.MESSAGE_WRONG_GUESS)
    }
}