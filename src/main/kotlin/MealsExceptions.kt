sealed class MealsExceptions : Exception() {
    data class GuessMealGamePassed(override val message: String) : MealsExceptions()
    data class GuessMealGameNotPassed(
        override val message: String
    ) : MealsExceptions()
}