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
        if (getItalianFoodForLargeGroupsUseCase.getItalianFoodForLargeGroups().isNotEmpty()) {
            getItalianFoodForLargeGroupsUseCase.getItalianFoodForLargeGroups().forEach { meal ->
                viewer.show(meal.name)
            }
        } else {
            viewer.show("No italian food found for large groups")
        }
    }

}