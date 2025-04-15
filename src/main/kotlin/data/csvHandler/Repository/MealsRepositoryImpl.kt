package data.csvHandler.Repository

import data.csvHandler.MealsCsvParser
import data.csvHandler.MealsCsvReader
import data.models.Meal
import logic.Repository.MealsRepository
import java.io.File

class MealsRepositoryImpl(
    private val csvReader: MealsCsvReader,
    private val mealsCsvParser: MealsCsvParser
):MealsRepository {
    override fun getAllMeals(): List<Meal> {
        val allMeals: MutableList<Meal> = mutableListOf()
            csvReader.readCsvFile().forEach { lineOfCsv->
            val newMeal = mealsCsvParser.parseOnLine(lineOfCsv)
            allMeals.add(newMeal)
        }
        return allMeals
    }
}