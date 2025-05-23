package presentation.features

import logic.usecase.MealSearchingByNameUseCase
import presentation.common.BaseUIController
import presentation.common.Reader
import presentation.common.Viewer
import utils.displayMeals

class SearchMealByNameUI(
    private val mealSearchingByNameUseCase: MealSearchingByNameUseCase,
    private val reader: Reader,
    private val viewer: Viewer
) : BaseUIController {
    override val id: Int = 2
    override val message: String = "$id- Enter any meal's name to search about it"

    override fun start() {
        viewer.show("Enter meal name")
        reader.getUserInput().toString().let { mealName ->
            try {
                mealSearchingByNameUseCase.searchAboutMealByName(mealName).displayMeals()
            } catch (exception: Exception) {
                viewer.show(exception.message.toString())
            }
        }
    }
}