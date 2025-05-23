package data.utilities


sealed class MealsExceptions : Exception() {
    data class InvalidDateFormatException(override val message: String) : MealsExceptions()
    data class DateParseException(override val message: String) : MealsExceptions()
    data class MealNotFoundException(override val message: String) : MealsExceptions()

    data class GuessMealGamePassed(override val message: String) : MealsExceptions()
    data class GuessMealGameNotPassed(
        override val message: String
    ) : MealsExceptions()
}