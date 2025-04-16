package presentation

import logic.usecase.mealIngredientsGame.GetGameScoreUseCase
import logic.usecase.mealIngredientsGame.GetIngredientGameRandomMealUseCase
import logic.usecase.mealIngredientsGame.MakeGuessUseCase


fun launchGameIngredientsGame(
    getRandomMealUseCase: GetIngredientGameRandomMealUseCase,
    makeGuessUseCase: MakeGuessUseCase,
    getGameScoreUseCase: GetGameScoreUseCase
) {
    println("this is the game ingredients game")
    println("you will be asked to guess the correct ingredient for a meal or a dish based only on it's name")
    println("you can only guess one time so make it count")
    println("let's get started")
    val randomMeal = getRandomMealUseCase()
    println("the meal is ${randomMeal.mealName}")
    randomMeal.options.forEachIndexed { index, ingredient ->
        println("ingredient ${index + 1}: $ingredient")
    }

    println("what is the correct ingredient? enter the name of the correct ingredient")

    readlnOrNull()?.let { userGuess ->
        try {
            makeGuessUseCase.invoke(guess = userGuess, correctGuess = randomMeal.correctAnswer)
        } catch (exception: MealsExceptions.GuessMealGameNotPassed) {
            println(exception.message)
        }finally {
            println("your current score is ${getGameScoreUseCase()}")
        }
    } ?: println("you didn't enter anything")

}