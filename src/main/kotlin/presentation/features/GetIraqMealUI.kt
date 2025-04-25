package presentation.features

import logic.usecase.GetAllIraqiMealsUseCase
import presentation.common.BaseUIController

class GetIraqMealUI(
    private val getAllIraqiMealsUseCase: GetAllIraqiMealsUseCase
) : BaseUIController {
    override val id: Int = 3
    override val message: String = "3- Get All Iraq meals "
    override fun start() {
        getAllIraqiMealsUseCase.getAllIraqiMeals().forEach {
            println(it)
        }
    }
}