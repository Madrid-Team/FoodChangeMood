package presentation.features

import logic.usecase.GetHealthyMealsUseCase
import presentation.common.BaseUIController
import presentation.common.Reader
import utils.displayMeals
import presentation.common.Viewer

class GetHealthyMealsUI(
    private val getHealthyMealsUseCase: GetHealthyMealsUseCase,
    private val reader: Reader,
    private val viewer: Viewer
) : BaseUIController {
    override val id: Int = 1
    override val message: String = "" +
            "1- Get a list of healthy fast food meals that can be prepared in 15 minutes or less, \n" +
            "with very low total fat, saturated fat, and carbohydrate."

    override fun start() {

        viewer.show("Enter your maximum count of healthy meals you want to proceed: ")
        reader.getUserInput()?.toIntOrNull().let { input ->
            if (input == null || input <= 0) {
                viewer.show("Please enter a positive number.\n")
            } else {
                try {
                    getHealthyMealsUseCase.execute(input).forEach {
                        viewer.show(it.toString())
                    }
                } catch (_: NoSuchElementException) {
                    viewer.show("There is no healthy meals")
                }
            }
        }

    }
}