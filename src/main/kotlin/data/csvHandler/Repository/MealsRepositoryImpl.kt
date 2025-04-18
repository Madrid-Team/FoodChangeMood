package data.csvHandler.Repository

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

    override fun addCorrectGuessedMealName(mealName: String) {
        correctGuessedMeals.add(mealName)
    }

    override fun getCorrectGuessedMealsNames(): List<String> {
        return correctGuessedMeals
    }

    override fun clearCorrectGuessedMealsNames() {
        correctGuessedMeals.clear()
    }
}