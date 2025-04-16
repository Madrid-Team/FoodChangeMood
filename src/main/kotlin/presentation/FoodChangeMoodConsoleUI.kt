package presentation

import logic.usecase.GetAllMealsUseCase

class FoodChangeMoodConsoleUI(
    private val getAllMealsUseCase: GetAllMealsUseCase
) {
    fun start() {
        showWelcome()
        presentFeatures()
    }

    private fun showWelcome() {
        println("Welcome to food change mood app")
    }

    private fun presentFeatures() {
        showOptions()
        val input = getUserInput()
        when (input) {

        }
        presentFeatures()
    }

    private fun showOptions() {
        TODO("Not yet implemented")
    }

    private fun getUserInput(): Int? {
        return readlnOrNull()?.toIntOrNull()
    }
}