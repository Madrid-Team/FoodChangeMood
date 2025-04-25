package presentation.features

import logic.usecase.SuggestNewKetoMealUseCase
import presentation.common.BaseUIController
import presentation.common.Reader
import presentation.common.Viewer

class KetoDietMealHelperUI(
    private val suggestNewKetoMealUseCase: SuggestNewKetoMealUseCase,
    private val reader: Reader,
    private val viewer: Viewer
) : BaseUIController {
    override val id: Int = 7
    override val message: String = "$id- Get one keto-friendly meal.."

    override fun start() {
        val alreadySuggestedIds: MutableSet<Int> = mutableSetOf()
        while (true) {
            try {
                val ketoMeal = suggestNewKetoMealUseCase.execute(alreadySuggestedIds.toSet())
                viewer.show("Name of keto meal : ${ketoMeal.name} \n")
                if (ketoMeal.description != null) {
                    viewer.show("and description of this keto : ${ketoMeal.description}")
                }

                viewer.show("Enter (1) if you like Keto meal to view it's details \n and (0) if you don't like it to suggest another Keto meal: ")
                val option = reader.getUserInput() ?: ""
                when (option) {
                    "1" -> {
                        viewer.show(ketoMeal.toString())
                        break
                    }

                    "0" -> {
                        viewer.show("lets try another one")
                        alreadySuggestedIds.add(ketoMeal.id)
                    }
                }
            } catch (exception: Exception) {
                exception.message?.let { viewer.show(it) }
                break
            }
        }
    }
}