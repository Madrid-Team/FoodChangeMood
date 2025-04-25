package presentation.features

import logic.usecase.GetAllIraqiMealsUseCase
import presentation.common.BaseUIController
import utils.displayMeals

class GetIraqMealUI(
    private val getAllIraqiMealsUseCase: GetAllIraqiMealsUseCase
) : BaseUIController {
    override val id: Int = 3
    override val message: String = "$id- Get All Iraq meals "
    override fun start() {
        getAllIraqiMealsUseCase.getAllIraqiMeals().displayMeals()
    }
}