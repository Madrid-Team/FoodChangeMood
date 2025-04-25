package presentation.features

import logic.usecase.GetHealthyMealsUseCase
import presentation.common.BaseUIController
import presentation.common.ConsoleReader

class GetHealthyMealsUI(
    private val getHealthyMealsUseCase: GetHealthyMealsUseCase,
    private val reader: ConsoleReader
) : BaseUIController {
    override val id: Int = 1
    override val message: String = "" +
            "1- Get a list of healthy fast food meals that can be prepared in 15 minutes or less, \n" +
            "with very low total fat, saturated fat, and carbohydrate."

    override fun start() {
        try {
            println("Enter your maximum count of healthy meals you want to proceed: ")
            reader.getUserInput()?.toIntOrNull().let { input ->
                if (input == null || input <= 0) {
                    println("Please enter a positive number.\n")
                } else {
                    getHealthyMealsUseCase.execute(input).forEach {
                        println(it)
                    }
                }
            }
        } catch (exception: Exception) {
            println(exception.message)
        }
    }
}