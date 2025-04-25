package presentation.features

import logic.usecase.GetHealthyMealsUseCase
import presentation.common.BaseUIController
import presentation.common.Viewer

class GetHealthyMealsUI(
    private val getHealthyMealsUseCase: GetHealthyMealsUseCase,
    private val viewer: Viewer
) : BaseUIController {
    override val id: Int = 1
    override val message: String = "" +
            "$id- Get a list of healthy fast food meals that can be prepared in 15 minutes or less, \n" +
            "with very low total fat, saturated fat, and carbohydrate."

    override fun start() {
        try {
            viewer.show("Enter your maximum count of healthy meals you want to proceed: ")
            val countOfHealthyMeals = readlnOrNull()?.toIntOrNull() ?: 0
            getHealthyMealsUseCase.execute(countOfHealthyMeals).forEach {
                viewer.show(it.toString())
            }
        } catch (exception: Exception) {
            exception.message?.let { viewer.show(it) }
        }
    }
}