package presentation.features

import data.models.Meal
import logic.usecase.SuggestNewKetoMealUseCase
import presentation.common.BaseUIController
import presentation.common.ConsoleReader
import presentation.common.ConsoleViewer

class KetoDietMealHelperUI(
    private val suggestNewKetoMealUseCase: SuggestNewKetoMealUseCase,
    private val reader: ConsoleReader,
    private val viewer: ConsoleViewer,
) : BaseUIController {
    override val id: Int = 7
    override val message: String = "7- Get one keto-friendly meal.."

    override fun start() {
        val alreadySuggestedIds: MutableSet<Int> = mutableSetOf()
        while (true) {
            try {
                val ketoMeal = suggestNewKetoMealUseCase.execute(alreadySuggestedIds.toSet())
                displayBriefOfMeal(ketoMeal)
                when (getUserOption()) {
                    "1" -> {
                        displayMealDetails(ketoMeal)
                        break
                    }

                    else -> {
                        viewer.show("lets try another one")
                        alreadySuggestedIds.add(ketoMeal.id)
                    }
                }
            } catch (_: NoSuchElementException) {
                viewer.show("There is no Meal Suggest!")
                break
            }
        }
    }


    private fun displayBriefOfMeal(meal: Meal) {
        viewer.show("Name of keto meal : ${meal.name} \n${meal.description?.let { "Description of this keto meal : $it" } ?: ""}")
    }

    private fun displayMealDetails(meal: Meal) {
        viewer.show(meal.toString())
    }

    private fun getUserOption(): String {
        while (true) {
            viewer.show("Enter (1) if you like Keto meal to view it's details \n and (0) if you don't like it to suggest another Keto meal: ")

            reader.getUserInput().let { option ->
                if (option == null) {
                    viewer.show("Please enter your right option.\n")
                } else {
                    return option
                }
            }
        }
    }
}