package presentation.features

import logic.usecase.MealSearchingByNameUseCase
import presentation.common.BaseUIController
import presentation.common.Reader

class SearchMealByNameUI(
    private val mealSearchingByNameUseCase: MealSearchingByNameUseCase,
    private val reader: Reader
) : BaseUIController {
    override val id: Int = 2
    override val message: String = "2- Enter any meal's name to search about it"

    override fun start() {
        println("Enter meal name")
        reader.getUserInput().toString().let { mealName ->
            try {
                mealSearchingByNameUseCase.searchAboutMealByName(mealName).forEach {
                    println(it)
                }
            } catch (exception: Exception) {
                println("$mealName not found")
            }
        }
    }
}