package presentation.features

import logic.usecase.GetMealsSuitableForGymUseCase
import presentation.common.BaseUIController
import presentation.common.Reader
import presentation.common.Viewer

class GymHelperUI(
    private val getMealsSuitableForGymUseCase: GetMealsSuitableForGymUseCase,
    private val viewer: Viewer,
    private val reader: Reader
) : BaseUIController {
    override val id: Int = 9
    override val message: String =
        "$id- Enter a desired amount of calories and protein,\n" +
                "and return a list of meals that match or approximate those values."


    override fun start() {
        println("Input the amount of calories you want")
        val calories = reader.readDouble()
        println("Input the amount of protein you want")
        val protein = reader.readDouble()

        if (calories != null && protein != null) {
            try {
                getMealsSuitableForGymUseCase.getMealsWithinCalorieAndProteinRange(calories, protein).forEach { meal ->
                    viewer.show(meal.name)
                }
            } catch (e: NoSuchElementException) {
                viewer.show(e.message.toString())
            }
        } else {
            viewer.show("Invalid input")
        }

    }
}