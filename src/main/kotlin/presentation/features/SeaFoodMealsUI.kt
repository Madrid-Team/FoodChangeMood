package presentation.features

import logic.usecase.GetAllSeafoodMealsUseCase
import presentation.common.BaseUIController

class SeaFoodMealsUI(
    private val getAllSeafoodMealsUseCase: GetAllSeafoodMealsUseCase
) : BaseUIController {
    override val id: Int = 14
    override val message: String = "14- You will get a list of all seafood meals sorted by protein content," +
            "from highest to lowest."

    override fun start() {
        println("Test Test .. Add your feature here")
    }
}