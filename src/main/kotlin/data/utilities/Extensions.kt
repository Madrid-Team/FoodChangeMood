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