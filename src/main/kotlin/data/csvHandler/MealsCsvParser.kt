package data.csvHandler

import data.models.Meal


class MealsCsvParser() {


    fun parseOnLineString(line: String): List<String> {
        return line.removeSurrounding("\"").split(",(?![^\\[]*\\])".toRegex())
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

    fun String.toListOfDoubles(): List<Double> {
        //TODO
        return emptyList()
    }

}