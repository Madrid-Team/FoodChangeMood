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
            1 -> TODO()
            2 -> TODO()
            3 -> TODO()
            4 -> TODO()
            5 -> TODO()
            6 -> TODO()
            7 -> TODO()
            8 -> TODO()
            9 -> TODO()
            10 -> TODO()
            11 -> TODO()
            12 -> TODO()
            13 -> TODO()
            14 -> TODO()
            else -> println("Invalid Input .. add another input between 1 to 14 please")
        }
        presentFeatures()
    }

    private fun showOptions() {
        println("\n\n please enter one of the following options")
        println(
            "1- Get a list of healthy fast food meals that can be prepared in 15 minutes or less," +
                    " with very low total fat, saturated fat, and carbohydrate."
        )
        println("2- Enter any meal's name to search about it")
        println(
            "3- Play a fun game ..\n" +
                    "- Get 10 get a list of healthy fast food meals that can be prepared in 15 minutes or less, " +
                    "with very low total fat, saturated fat, and carbohydrate."
        )
        println(
            "4- Guess game .. you will show the random meal name and you will guess it's preparation time.\n" +
                    "- you have 3 attempts .. After each attempt, The guessed time is correct, too low, or too high.\n" +
                    "- If all attempts are incorrect, you will see the correct time."
        )
        println(
            "5- Get one sweet that not contains no eggs .. \n" +
                    "- Press 1 if you like it and want more details about this meal.\n" +
                    "- Press 2 if you dislike it and want another sweet."
        )
        println(
            "6- Get one keto-friendly meal .. \n" +
                    "- Press 1 if you like it and want more details about this keto meal.\n" +
                    "- Press 2 if you dislike and want another keto meal."
        )
        println(
            "7- Add a date and get list of meals added on this date.\n" +
                    "- Enter the Id of any meal and you will get more details about it."
        )
        println(
            "8- Enter a desired amount of calories and protein," +
                    "and return a list of meals that match or approximate those values."
        )
        println("9- Enter a country name and you will get up to 20 meals related to this country.")
        println(
            "10- Ingredient Game ..\n" +
                    "- you will get a meal name and three ingredient options.\n" +
                    "- you can guess once .. A correct guess earns 1000 points , an incorrect guess ends the game.\n" +
                    "- The game also ends after 15 correct answers."
        )
        println("11- You will get random list of 10 meals that include potatoes in their ingredients.")
        println(
            "12- You will get a random meal more than 700 calories\n" +
                    "- Press 1 if you like it and want more details about this meal.\n" +
                    "- Press 2 if you dislike it and want another sweet."
        )
        println(
            "13- You will get a list of all seafood meals sorted by protein content," +
                    "from highest to lowest."
        )
        println("14- You will get Italian meals suitable for large groups.")
    }

    private fun getUserInput(): Int? {
        return readlnOrNull()?.toIntOrNull()
    }
}