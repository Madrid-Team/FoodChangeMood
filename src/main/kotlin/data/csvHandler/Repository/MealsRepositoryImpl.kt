package data.csvHandler.Repository

import data.csvHandler.MealsCsvParser
import data.csvHandler.MealsCsvReader
import data.models.Meal
import logic.Repository.MealsRepository

class MealsRepositoryImpl(
    private val csvReader: MealsCsvReader,
    private val mealsCsvParser: MealsCsvParser
):MealsRepository {

    private val allMeals: MutableList<Meal> = mutableListOf()

    init {
        readMealsFromCsvFile()
    }
    override fun getAllMeals(): List<Meal>  = allMeals

    private fun readMealsFromCsvFile(): List<Meal> {

            csvReader.readCsvFile().forEach { lineOfCsv->
                val newMeal = mealsCsvParser.parseOnLine(lineOfCsv)
                allMeals.add(newMeal)
            }
        return allMeals
    }
}