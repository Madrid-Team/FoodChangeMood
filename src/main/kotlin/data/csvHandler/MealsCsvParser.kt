package data.csvHandler

import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import data.utilities.parseDateString
import data.utilities.splitIgnoringQuotedCommas

class MealsCsvParser() {

    fun parseOnLine(line: String): Meal{
        val mealInfo = splitIgnoringQuotedCommas(line)

        return Meal(
            name = mealInfo[ColumnIndex.NAME],
            contributorId = mealInfo[ColumnIndex.CONTRIBUTOR_ID].toInt(),
            id = mealInfo[ColumnIndex.ID].toInt(),
            minutes = mealInfo[ColumnIndex.MINUTES].toInt(),
            description = mealInfo[ColumnIndex.DESCRIPTION],
            ingredients = Ingredients(
                ingredients = mealInfo[ColumnIndex.INGREDIENTS].toListOfStrings(),
                ingredientsCount = mealInfo[ColumnIndex.N_INGREDIENTS].toInt()
            ),
            tags = mealInfo[ColumnIndex.TAGS].toListOfStrings(),
            nutrition = getNutrition(mealInfo[ColumnIndex.NUTRITION]),
            steps = Steps(
                steps = mealInfo[ColumnIndex.STEPS].toListOfStrings(),
                stepsCount = mealInfo[ColumnIndex.N_STEPS].toInt()
            ),
            submitted = parseDateString(mealInfo[ColumnIndex.SUBMITTED]),
        )
    }


    private fun String.toListOfStrings(): List<String> {
        return this.trim() // Remove outer spaces
            .removeSurrounding("\"")
            .removePrefix("[")
            .removeSuffix("]")
            .split(",")
            .map { it.trim().replace("'","") }
            .filter { it.isNotEmpty() }
    }

    private fun List<String>.toListOfDoubles(): List<Double> {
        return this.map {number-> number.toDouble() }
    }

    private fun getNutrition(nutritionText:String):Nutrition{
        val nutritionList = nutritionText.toListOfStrings().toListOfDoubles()
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
