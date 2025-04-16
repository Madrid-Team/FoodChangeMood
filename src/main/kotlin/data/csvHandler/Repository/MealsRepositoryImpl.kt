package data.csvHandler.Repository

import MealsExceptions.GuessMealGamePassed
import data.csvHandler.MealsCsvParser
import data.csvHandler.MealsCsvReader
import data.models.Meal
import logic.Repository.MealsRepository

class MealsRepositoryImpl(
    private val csvReader: MealsCsvReader,
    private val mealsCsvParser: MealsCsvParser
) : MealsRepository {
    private val correctGuessedMeals: MutableList<String> = mutableListOf()

    override fun getAllMeals(): List<Meal> {
        val allMeals: MutableList<Meal> = mutableListOf()
        csvReader.readCsvFile().forEach { lineOfCsv ->
            val newMeal = mealsCsvParser.parseOnLine(lineOfCsv)
            allMeals.add(newMeal)
        }
        return allMeals
    }

    override fun addCorrectGuessedMealName(mealName: String): String {
        correctGuessedMeals.add(mealName)
        return if (correctGuessedMeals.size == 15) {
            correctGuessedMeals.clear()
            throw (GuessMealGamePassed("You have guessed all the meals correctly!"))
        } else {
            "that's correct! You have guessed ${correctGuessedMeals.size} meals correctly!"
        }
    }

    override fun getCorrectGuessedMealsNames(): List<String> {
        return correctGuessedMeals
    }
}