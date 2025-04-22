package presentation.features

import logic.usecase.MealSearchingByNameUseCase
import presentation.common.BaseUIController

class SearchMealByNameUI(
    private val mealSearchingByNameUseCase: MealSearchingByNameUseCase
) : BaseUIController {
    override val id: Int = 2
    override val message: String = "2- Enter any meal's name to search about it"

    override fun start() {
        TODO("Not yet implemented")
    }
}