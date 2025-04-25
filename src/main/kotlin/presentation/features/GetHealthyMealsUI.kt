package presentation.features

import logic.usecase.GetHealthyMealsUseCase
import presentation.common.BaseUIController
import utils.displayMeals

class GetHealthyMealsUI(
    private val getHealthyMealsUseCase: GetHealthyMealsUseCase
) : BaseUIController {
    override val id: Int = 1
    override val message: String = "" +
            "1- Get a list of healthy fast food meals that can be prepared in 15 minutes or less, \n" +
            "with very low total fat, saturated fat, and carbohydrate."

    override fun start() {
        try {
            println("Enter your maximum count of healthy meals you want to proceed: ")
            val countOfHealthyMeals = readlnOrNull()?.toIntOrNull() ?: 0

            getHealthyMealsUseCase.execute(countOfHealthyMeals).displayMeals()

        } catch (exception: Exception) {
            println(exception.message)
        }
    }
}