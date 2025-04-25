package presentation.features

import logic.usecase.ShowRandomMealsIncludePotatoesUseCase
import presentation.common.BaseUIController
import utils.displayMeals
import presentation.common.Viewer

class ILovePotatoUI(
    private val showRandomMealsIncludePotatoesUseCase: ShowRandomMealsIncludePotatoesUseCase,
    private val viewer: Viewer
) : BaseUIController {
    override val id: Int = 12
    override val message: String =
        "$id- You will get random list of 10 meals that include potatoes in their ingredients."

    override fun start() {
        showRandomMealsIncludePotatoesUseCase.showRandomMealsIncludePotatoes().displayMeals()
    }
}