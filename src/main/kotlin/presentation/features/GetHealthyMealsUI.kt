package presentation.features

import logic.usecase.GetHealthyMealsUseCase
import presentation.common.BaseUIController
import presentation.common.ConsoleReader
import presentation.common.ConsoleViewer

class GetHealthyMealsUI(
    private val getHealthyMealsUseCase: GetHealthyMealsUseCase,
    private val reader: ConsoleReader,
    private val viewer: ConsoleViewer,
) : BaseUIController {
    override val id: Int = 1
    override val message: String = "" +
            "1- Get a list of healthy fast food meals that can be prepared in 15 minutes or less, \n" +
            "with very low total fat, saturated fat, and carbohydrate."

    override fun start() {
        try {
            viewer.show("Enter your maximum count of healthy meals you want to proceed: ")
            reader.getUserInput()?.toIntOrNull().let { input ->
                if (input == null || input <= 0) {
                    viewer.show("Please enter a positive number.\n")
                } else {
                    getHealthyMealsUseCase.execute(input).forEach {
                        viewer.show(it.toString())
                    }
                }
            }
        } catch (exception: Exception) {
            println(exception.message)
        }
    }
}