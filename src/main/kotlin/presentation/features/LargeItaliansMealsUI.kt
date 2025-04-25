package presentation.features

import logic.usecase.GetItalianFoodForLargeGroupsUseCase
import presentation.common.BaseUIController
import presentation.common.Viewer

class LargeItaliansMealsUI(
    private val getItalianFoodForLargeGroupsUseCase: GetItalianFoodForLargeGroupsUseCase,
    private val viewer: Viewer
) : BaseUIController {
    override val id: Int = 15
    override val message: String =
        "15- You will get Italian meals suitable for large groups."

    override fun start() {
        try {
            getItalianFoodForLargeGroupsUseCase.getItalianFoodForLargeGroups().forEach { meal ->
                viewer.show(meal.name)
            }
        } catch (e: Exception) {
            e.message?.let { viewer.show(it) }
        }
    }
}