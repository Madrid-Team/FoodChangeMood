package presentation.features

import logic.usecase.GetItalianFoodForLargeGroupsUseCase
import presentation.common.BaseUIController

class LargeItaliansMealsUI(
    private val getItalianFoodForLargeGroupsUseCase: GetItalianFoodForLargeGroupsUseCase
) : BaseUIController {
    override val id: Int = 15
    override val message: String =
        "15- You will get Italian meals suitable for large groups."

    override fun start() {
        try {
            getItalianFoodForLargeGroupsUseCase.getItalianFoodForLargeGroups().forEach {
                println(it)
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}