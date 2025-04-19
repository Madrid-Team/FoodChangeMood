package logic.usecase.mealIngredientsGame

import MealsExceptions
import data.csvHandler.Tags.GameScore.MAX_CORRECT_GUESSES
import data.csvHandler.Tags.UserMessages
import logic.Repository.MealsRepository

class MakeGuessUseCase(private val repository: MealsRepository) {

    operator fun invoke(guess: String, correctGuess: String): String {
        return if (guess == correctGuess) {
            repository.addCorrectGuessedMealName(guess)
            val correctGuessedMeals = repository.getCorrectGuessedMealsNames()
            if (correctGuessedMeals.size < MAX_CORRECT_GUESSES) {
                repository.clearCorrectGuessedMealsNames()
                UserMessages.MESSAGE_CORRECT_GUESS.format(correctGuessedMeals.size)
            } else {
                throw MealsExceptions.GuessMealGamePassed(UserMessages.MESSAGE_ALL_GUESSES_PASSED)
            }
        } else
            throw MealsExceptions.GuessMealGameNotPassed(UserMessages.MESSAGE_WRONG_GUESS)
    }
}