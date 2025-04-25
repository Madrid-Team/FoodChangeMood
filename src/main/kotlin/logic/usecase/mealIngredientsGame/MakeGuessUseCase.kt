package logic.usecase.mealIngredientsGame

import logic.Repository.MealsRepository

class MakeGuessUseCase(private val repository: MealsRepository) {

    operator fun invoke(guess: String, correctGuess: String): String {
        return if (guess == correctGuess) {
            repository.addCorrectGuessedMealName(guess)
            val correctGuessedMeals = repository.getCorrectGuessedMealsNames()
            if (correctGuessedMeals.size < 15) {
                "that's correct! You have guessed ${correctGuessedMeals.size} meals correctly!"
            } else {
                repository.clearCorrectGuessedMealsNames()
                throw MealsExceptions.GuessMealGamePassed("You have guessed all the meals correctly!")
            }
        } else {
            repository.clearCorrectGuessedMealsNames()
            throw MealsExceptions.GuessMealGameNotPassed("ooh, you didn't guess the meal name")
        }
    }
}