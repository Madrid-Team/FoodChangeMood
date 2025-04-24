package presentation.features

import logic.usecase.ShowRandomMealsIncludePotatoesUseCase
import presentation.common.BaseUIController

class ILovePotatoUI(
    private val showRandomMealsIncludePotatoesUseCase: ShowRandomMealsIncludePotatoesUseCase
) : BaseUIController {
    override val id: Int = 12
    override val message: String =
        "12- You will get random list of 10 meals that include potatoes in their ingredients."

    override fun start() {
        try {
            showRandomMealsIncludePotatoesUseCase.showRandomMealsIncludePotatoes().forEach {
                println(it)
            }
        } catch (exception: Exception) {
            println(exception.message)
        }

    }
}