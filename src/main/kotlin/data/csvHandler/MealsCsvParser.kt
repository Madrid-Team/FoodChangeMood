package data.csvHandler

import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import data.utilities.*


class MealsCsvParser {

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
            .removeSurroundingQuotes()
            .removeBrackets()
            .splitByComma()
            .trimElements()
            .removeSingleQuotesFromElements()
            .removeEmptyElements()
    }

    private fun List<String>.toListOfDoubles(): List<Double> {
        return this.map {number-> number.toDouble() }
    }

    private fun getNutrition(nutritionText:String):Nutrition{
        val nutritionList = nutritionText.toListOfStrings().toListOfDoubles()
        return Nutrition(
            calories = nutritionList[ColumnIndex.CALORIES],
            totalFat = nutritionList[ColumnIndex.TOTAL_FATS],
            sugar = nutritionList[ColumnIndex.SUGAR],
            sodium = nutritionList[ColumnIndex.SODIUM],
            saturatedFat = nutritionList[ColumnIndex.SATURATED_FATS],
            carbohydrates = nutritionList[ColumnIndex.CARBOHYDRATES],
            protein = nutritionList[ColumnIndex.PROTEIN]
        )
    }


}
