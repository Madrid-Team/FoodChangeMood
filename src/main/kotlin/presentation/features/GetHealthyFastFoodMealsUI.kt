package presentation.features

import logic.usecase.GetHealthyFoodUseCase
import presentation.common.BaseUIController

class GetHealthyFastFoodMealsUI(
    private val getHealthyFoodUseCase: GetHealthyFoodUseCase
) : BaseUIController {
    override val id: Int = 1
    override val message: String =
        "1- Get a list of healthy fast food meals that can be prepared in 15 minutes or less," +
                " with very low total fat, saturated fat, and carbohydrate."

    override fun start() {
        println("Test Test .. Add your feature here")
    }
}