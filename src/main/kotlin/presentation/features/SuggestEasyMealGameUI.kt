package presentation.features

import logic.usecase.SuggestEasyMealUseCase
import presentation.common.BaseUIController

class SuggestEasyMealGameUI(
    private val suggestEasyMealUseCase: SuggestEasyMealUseCase
) : BaseUIController {
    override val id: Int = 4
    override val message: String =
        "4- Play a fun game ..\n" +
                "- Get 10 get a list of healthy fast food meals that can be prepared in 15 minutes or less, " +
                "with very low total fat, saturated fat, and carbohydrate."

    override fun start() {
        suggestEasyMealUseCase.execute(10).forEach {
            println(it)
        }
    }
}