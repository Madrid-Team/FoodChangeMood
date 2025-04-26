package data.csvHandler.Repository

import data.csvHandler.MealsCsvParser
import data.csvHandler.MealsCsvReader
import data.models.Meal
import logic.Repository.MealsRepository
import java.util.*

class MealsRepositoryImpl(
    private val csvReader: MealsCsvReader,
    private val mealsCsvParser: MealsCsvParser
):MealsRepository {
    private val correctGuessedMeals: MutableList<String> = mutableListOf()

    private val allMeals: MutableList<Meal> = mutableListOf()
    private val mealsByDate: MutableMap<Date, MutableList<Meal>> = mutableMapOf()

    init {
        readMealsFromCsvFile()
    }


    override fun getAllMeals(): List<Meal> = allMeals

    override fun getMealsByDate(date: Date): List<Meal> {
        return mealsByDate[date] ?: emptyList()
    }

    override fun getMealByIdFromSelectedDate(id: Int): Meal? {
        for (mealsForDate in mealsByDate.values) {
            val meal = mealsForDate.find { it.id == id }
            if (meal != null) {
                return meal
            }
        }
        return null
    }

    private fun readMealsFromCsvFile(): List<Meal> {
        csvReader.readCsvFile().forEach { lineOfCsv ->
            val newMeal = mealsCsvParser.parseOnLine(lineOfCsv)
            allMeals.add(newMeal)
            mealsByDate.getOrPut(newMeal.submitted) { mutableListOf() }.add(newMeal)
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