package presentation.features

import logic.usecase.SuggestEasyMealUseCase
import presentation.common.BaseUIController
import presentation.common.ConsoleViewer

class SuggestEasyMealGameUI(
    private val suggestEasyMealUseCase: SuggestEasyMealUseCase,
    private val viewer: ConsoleViewer,
) : BaseUIController {
    override val id: Int = 4
    override val message: String = "" +
            "4- Play a fun game ..\n" +
            "- Get 10 random meals that are easy to prepared in 30 minutes or less, \n" +
            "has 5 ingredients or fewer and can be prepared in 6 steps or fewer."

    override fun start() {
        try {
            suggestEasyMealUseCase.execute(10).forEach {
                viewer.show(it.toString())
            }
        } catch (_: NoSuchElementException) {
            viewer.show("There is no easy food suggestion")
        }
    }
}