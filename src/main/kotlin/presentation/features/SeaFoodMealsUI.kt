package presentation.features

import data.models.SeafoodMeal
import logic.usecase.GetAllSeafoodMealsUseCase
import presentation.common.BaseUIController

class SeaFoodMealsUI(
    private val getAllSeafoodMealsUseCase: GetAllSeafoodMealsUseCase
) : BaseUIController {
    override val id: Int = 14
    override val message: String = "14- You will get a list of all seafood meals sorted by protein content," +
            "from highest to lowest."

    override fun start() {
        getAllSeafoodMealsUseCase.getAllSeafoodMeals().forEachIndexed() { index, seafoodMeal ->
            displaySeaFoodMeal(rank = index+1, meal = seafoodMeal)
        }
    }

    private fun displaySeaFoodMeal(rank:Int , meal:SeafoodMeal){
        println("Rank: $rank,Name: ${meal.name} ,Protein: ${meal.protein}")
    }
}