package data.csvHandler.Repository

import data.csvHandler.MealsCsvParser
import data.csvHandler.MealsCsvReader
import data.csvHandler.MealsJsonConverter
import data.csvHandler.MealsJsonReader
import data.models.Meal
import logic.Repository.MealsRepository
import java.io.File
import java.util.Date

class MealsRepositoryImpl(
    private val csvReader: MealsCsvReader,
    private val mealsCsvParser: MealsCsvParser,
    private val jsonConverter: MealsJsonConverter = MealsJsonConverter()

):MealsRepository {
    private val correctGuessedMeals: MutableList<String> = mutableListOf()

    private val allMeals: MutableList<Meal> = mutableListOf()
    private val mealsByDate: MutableMap<Date, MutableList<Meal>> = mutableMapOf()



     private val jsonFile = File("meals.json")

    init {
        if (jsonFile.exists()) {
            try {
                readMealsFromJsonFile()
            } catch (e: Exception) {
                allMeals.clear()
                mealsByDate.clear()
                readMealsFromCsvFile()
            }
        } else {
            readMealsFromCsvFile()
        }


     }

    private fun readMealsFromJsonFile(): List<Meal> {
        val jsonReader = MealsJsonReader(jsonFile)
        val meals = jsonReader.readJsonFile()

        meals.forEach { meal ->
            allMeals.add(meal)
            mealsByDate.getOrPut(meal.submitted) { mutableListOf() }.add(meal)
        }

        return allMeals
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
        jsonConverter.saveToJsonFile(allMeals, jsonFile)

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