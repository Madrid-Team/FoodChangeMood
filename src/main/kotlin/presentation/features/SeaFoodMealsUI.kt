package presentation.features

import data.models.SeafoodMeal
import logic.usecase.GetAllSeafoodMealsUseCase
import presentation.common.BaseUIController
import presentation.common.Viewer

class SeaFoodMealsUI(
    private val getAllSeafoodMealsUseCase: GetAllSeafoodMealsUseCase,
    private val viewer: Viewer
) : BaseUIController {
    override val id: Int = 14
    override val message: String = "$id- You will get a list of all seafood meals sorted by protein content," +
            "from highest to lowest."

    override fun start() {
        getAllSeafoodMealsUseCase.getAllSeafoodMeals().forEach {
            println(it)
        }
    }
}