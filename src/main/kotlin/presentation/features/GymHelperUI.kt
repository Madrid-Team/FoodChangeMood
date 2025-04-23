package presentation.features

import logic.usecase.GetMealsSuitableForGymUseCase
import presentation.common.BaseUIController

class GymHelperUI(
    private val getMealsSuitableForGymUseCase: GetMealsSuitableForGymUseCase
) : BaseUIController {
    override val id: Int = 9
    override val message: String =
        "9- Enter a desired amount of calories and protein,\n" +
                "and return a list of meals that match or approximate those values."


    override fun start() {
        println("Test Test .. Add your feature here")
    }
}