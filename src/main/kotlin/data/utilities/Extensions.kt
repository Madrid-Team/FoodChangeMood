package data.utilities

import java.text.SimpleDateFormat
import java.util.Date

fun splitIgnoringQuotedCommas(input: String): List<String> {
    val pattern = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"
    return input.split(pattern.toRegex())
}

fun parseDateString(dateString: String): Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    dateFormat.isLenient = false

    if (!dateString.matches("\\d{4}-\\d{2}-\\d{2}".toRegex())) {
        throw MealsExceptions.InvalidDateFormatException("Date format must be yyyy-MM-dd")
    }

    try {
        return dateFormat.parse(dateString) ?: throw MealsExceptions.DateParseException("Invalid date")
    } catch (e: Exception) {
        throw MealsExceptions.DateParseException("Failed to parse date: ${e.message}")
    }
}



val String.Companion.fileNotFound:String get() = "File not found"

val String.Companion.empty: String get() = ""
val Char.Companion.doubleQuotes: Char get() = '"'
val String.Companion.singleQuote: String get() = "'"
val String.Companion.comma: String get() = ","
val String.Companion.openBracket: String get() = "["
val String.Companion.closeBracket: String get() = "]"

fun List<String>.dropHeader(): List<String> = this.drop(1)

fun String.appendLine(line: String): String = if (this.isEmpty()) line else this + line

fun String.removeSurroundingQuotes(): String = this.removeSurrounding(Char.doubleQuotes.toString())
fun String.removeBrackets(): String = this.removePrefix(String.openBracket).removeSuffix(String.closeBracket)
fun List<String>.removeSingleQuotesFromElements(): List<String> =
    this.map { it.replace(String.singleQuote, String.empty) }
fun String.splitByComma(): List<String> = this.split(String.comma)

fun List<String>.trimElements(): List<String> = this.map { it.trim() }
fun List<String>.removeEmptyElements(): List<String> = this.filter { it.isNotEmpty() }

val Int.isEven: Boolean
    get() = this % 2 == 0