package presentation.features

import logic.usecase.GetKetoMealSuggestUseCase
import presentation.common.BaseUIController

class KetoDietMealHelperUI(
    private val getKetoMealSuggestUseCase: GetKetoMealSuggestUseCase
) : BaseUIController {
    override val id: Int = 7
    override val message: String = "7- Get one keto-friendly meal.."
    override fun start() {
        TODO("Not yet implemented")
    }
}