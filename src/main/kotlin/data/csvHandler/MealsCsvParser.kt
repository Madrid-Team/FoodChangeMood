package data.csvHandler

import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import kotlin.math.min


class MealsCsvParser() {

    fun parseOnLine(line: String): Meal{
        val mealInfo = line.removeSurrounding("\"").split(",(?![^\\[]*\\])".toRegex())
        return Meal(
            name = mealInfo[ColumnIndex.NAME],
            id = mealInfo[ColumnIndex.ID].toLong(),
            minutes = mealInfo[ColumnIndex.MINUTES].toInt(),
            description = mealInfo[ColumnIndex.DESCRIPTION],
            ingredients = Ingredients(
                ingredients = mealInfo[ColumnIndex.INGREDIENTS].toListOfStrings(),
                ingredientsCount = mealInfo[ColumnIndex.N_INGREDIENTS].toInt()
            ),
            tags = mealInfo[ColumnIndex.TAGS].toListOfStrings(),
            nutrition = getNutrition(mealInfo[ColumnIndex.NUTRITION]),
            steps = Steps(
                steps = mealInfo[ColumnIndex.STEPS].toListOfStrings() ,
                stepsCount = mealInfo[ColumnIndex.N_STEPS].toInt()
            ),
            submitted = mealInfo[ColumnIndex.STEPS],
        )
    }

    fun getMeals(): List<Meal> {
        //@TODO
        return emptyList()
    }

    fun String.toListOfStrings(): List<String> {
        return this.trim() // Remove outer spaces
            .removeSurrounding("\"")
            .removePrefix("[")
            .removeSuffix("]")
            .split(",")
            .map { it.trim().removeSurrounding("'") }
            .filter { it.isNotEmpty() }
    }

    fun List<String>.toListOfDoubles(): List<Double> {
        return this.map {number-> number.toDouble() }
    }

    fun getNutrition(nutritionText:String):Nutrition{
        val nutritionList = nutritionText.split(",").toListOfDoubles()
        return Nutrition(
            calories = nutritionList[0],
            totalFat = nutritionList[1],
            sugar = nutritionList[2],
            sodium = nutritionList[3],
            saturatedFat = nutritionList[4],
            carbohydrates = nutritionList[5],
            protein = nutritionList[6]
        )
    }

}