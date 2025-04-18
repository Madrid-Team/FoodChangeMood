package presentation

import logic.usecase.*

class FoodChangeMoodConsoleUI(
    private val exploreOtherCountriesFoodUseCase: ExploreOtherCountriesFoodUseCase,
    private val mealSearchingUseCase: MealSearchingUseCase,
    private val getTopHealthyFastFoodUseCase: GetTopHealthyFastFoodUseCase,
    private val easyFoodSuggestionUseCase: GetTenEasyFoodSuggestionUseCase,
    private val getSweetsWithNoEggsUseCase: GetSweetsWithNoEggsUseCase,
    private val guessGameConsoleUi: GuessGameConsoleUi,
    private val getKetoMealSuggestUseCase: GetKetoMealSuggestUseCase,
    private val getMealsSuitableForGymUseCase: GetMealsSuitableForGymUseCase,
    private val suggestMealWithHighCalorieUseCase: SuggestMealWithHighCalorieUseCase,
    private val getAllSeafoodMealsUseCase: GetAllSeafoodMealsUseCase,
    private val getItalianFoodForLargeGroupsUseCase: GetItalianFoodForLargeGroupsUseCase,
    private val showRandomMealsIncludePotatoesUseCase: ShowRandomMealsIncludePotatoesUseCase,
    private val getAllIraqiMealsUseCase: GetAllIraqiMealsUseCase,
    private val mealIngredientsGameUI: MealIngredientsGameUI,
    private val searchMealsByDateUI: SearchMealsByDateUI
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
        val input = getUserIntInput()
        when (input) {
            1 -> getHealthyFastFoodMeals()
            2 -> searchMealByName()
            3 -> getAllIraqiMeals()
            4 -> getEasySuggestedMeals()
            5 -> showGuessGame()
            6 -> getSweetWithNoEggs()
            7 -> getOneRandomKetoMeal()
            8 -> searchMealByDate()
            9 -> getSuitableGymMeals()
            10 -> exploreOtherCountriesFoodCulture()
            11 -> getMealIngredients()
            12 -> getTenRandomMealsContainsPotatoes()
            13 -> suggestMealWithHighCalories()
            14 -> getAllSeafoodMeals()
            15 -> getItalianFoodForLargeGroup()
            else -> println("Invalid Input")
        }
        letUserTryAgain()
    }

    private fun letUserTryAgain() {
        println("If you want to continue press 1 if you want to end the program press 0")
        getUserIntInput().let {
            when (it) {
                1 -> {
                    presentFeatures()
                }

                0 -> {
                    return
                }

                else -> {
                    println("Invalid Input")
                    letUserTryAgain()
                }
            }
        }
    }

    private fun searchMealByDate() {
        searchMealsByDateUI.start()
    }

    private fun getMealIngredients() {
        mealIngredientsGameUI.start()
    }

    private fun getSuitableGymMeals() {
        try {
            println("Input the amount of calories you want")
            val calories = getUserDoubleInput()
            println("Input the amount of protein you want")
            val protein = getUserDoubleInput()
            if (calories != null && protein != null) {
                val meals = getMealsSuitableForGymUseCase.getNameofGymMeals(calories, protein)
                if (meals.isEmpty()) {
                    println("No meals found matching the specified values.")
                    return
                }
                meals.forEach {
                    println(it)
                }
            }
        } catch (exception: Exception) {
            println(exception.message)
        }
    }

    private fun getAllIraqiMeals() {
        getAllIraqiMealsUseCase.getAllIraqiMeals().forEach {
            println(it)
        }
    }

    private fun getTenRandomMealsContainsPotatoes() {
        showRandomMealsIncludePotatoesUseCase.showRandomMealsIncludePotatoes().forEach {
            println(it)
        }
    }

    private fun getItalianFoodForLargeGroup() {
        getItalianFoodForLargeGroupsUseCase.getItalianFoodForLargeGroups().forEach {
            println(it)
        }
    }

    private fun getAllSeafoodMeals() {
        getAllSeafoodMealsUseCase.getAllSeafoodMeals().forEach {
            println(it)
        }
    }

    private fun suggestMealWithHighCalories() {
        println(suggestMealWithHighCalorieUseCase.suggestRandomHighCalorieMeal())
    }

    private fun getOneRandomKetoMeal() {
        println(getKetoMealSuggestUseCase.getKetoMeal())
    }

    private fun showGuessGame() {
        guessGameConsoleUi.startGuessGame()
    }

    private fun getEasySuggestedMeals() {
        easyFoodSuggestionUseCase.getTenEasyFoodSuggestion().forEach {
            println(it)
        }
    }

    private fun getHealthyFastFoodMeals() {
        getTopHealthyFastFoodUseCase.getTopHealthyFastFood().forEach {
            println(it)
        }
    }

    private fun searchMealByName() {
        println("Enter meal name")
        readlnOrNull()?.let { mealName ->
            try {
                mealSearchingUseCase.mealSearchingByName(mealName).forEach {
                    println(it)
                }
            } catch (exception: Exception) {
                println("$mealName not found")
            }
        }
    }

    private fun exploreOtherCountriesFoodCulture() {
        println("Enter country name you want to search about")
        readlnOrNull()?.let { countryName ->
            try {
                exploreOtherCountriesFoodUseCase.getRandomMeals(countryName).forEach {
                    println(it)
                }

            } catch (exception: Exception) {
                println("$countryName not found")
            }
        }
    }

    private fun getSweetWithNoEggs() {
        while (true) {
            val sweet = getSweetsWithNoEggsUseCase.getfSweetFreeEggs()

            println("Name of sweet with no eggs : ${sweet.name} \n and description of this sweet : ${sweet.description}")

            println(" Enter yes if you like sweet to view it's details \n and no if you don't like it to suggest another sweet with no eggs ")
            when (getLikeOrDislikeInput()) {
                "yes" -> {
                    println(sweet)
                    break
                }

                "no" -> {
                    println("lets try another one")
                }
            }
        }
    }

    private fun getLikeOrDislikeInput(): String? {
        return readlnOrNull()?.trim()?.lowercase()
    }

    private fun showOptions() {
        println("\n\n please enter one of the following options")
        println(
            "1- Get a list of healthy fast food meals that can be prepared in 15 minutes or less," +
                    " with very low total fat, saturated fat, and carbohydrate."
        )
        println("2- Enter any meal's name to search about it")
        println("3- Get All Iraq meals ")

        println(
            "4- Play a fun game ..\n" +
                    "- Get 10 get a list of healthy fast food meals that can be prepared in 15 minutes or less, " +
                    "with very low total fat, saturated fat, and carbohydrate."
        )
        println(
            "5- Guess game .. you will show the random meal name and you will guess it's preparation time.\n" +
                    "- you have 3 attempts .. After each attempt, The guessed time is correct, too low, or too high.\n" +
                    "- If all attempts are incorrect, you will see the correct time."
        )
        println(
            "6- Get one sweet that not contains no eggs .. \n" +
                    "- Write yes if you like it and want more details about this meal.\n" +
                    "- Write no if you dislike it and want another sweet."
        )
        println(
            "7- Get one keto-friendly meal .. \n" +
                    "- Press 1 if you like it and want more details about this keto meal.\n" +
                    "- Press 2 if you dislike and want another keto meal."
        )
        println(
            "8- Add a date and get list of meals added on this date.\n" +
                    "- Enter the Id of any meal and you will get more details about it."
        )
        println(
            "9- Enter a desired amount of calories and protein," +
                    "and return a list of meals that match or approximate those values."
        )
        println("10- Enter a country name and you will get up to 20 meals related to this country.")
        println(
            "11- Ingredient Game ..\n" +
                    "- you will get a meal name and three ingredient options.\n" +
                    "- you can guess once .. A correct guess earns 1000 points , an incorrect guess ends the game.\n" +
                    "- The game also ends after 15 correct answers."
        )
        println("12- You will get random list of 10 meals that include potatoes in their ingredients.")
        println(
            "13- You will get a random meal more than 700 calories\n" +
                    "- Press 1 if you like it and want more details about this meal.\n" +
                    "- Press 2 if you dislike it and want another sweet."
        )
        println(
            "14- You will get a list of all seafood meals sorted by protein content," +
                    "from highest to lowest."
        )
        println("15- You will get Italian meals suitable for large groups.")
        println("----------------------------------------------------------------------------")
    }

    private fun getUserIntInput(): Int? {
        return readlnOrNull()?.toIntOrNull()
    }

    private fun getUserDoubleInput(): Double? {
        return readlnOrNull()?.toDoubleOrNull()
    }
}