package presentation.features

import logic.usecase.GetHealthyMealsUseCase
import presentation.common.BaseUIController

class GetHealthyMealsUI(
    private val getHealthyMealsUseCase: GetHealthyMealsUseCase
) : BaseUIController {
    override val id: Int = 1
    override val message: String =
        "1- Get a list of healthy fast food meals that can be prepared in 15 minutes or less," +
                " with very low total fat, saturated fat, and carbohydrate."

    override fun start() {
        try {
            getHealthyMealsUseCase.execute(10).forEach {
                println(it)
            }
        } catch (exception: Exception) {
            println(exception.message)
        }
    }
}