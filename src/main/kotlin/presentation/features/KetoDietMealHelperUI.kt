package presentation.features

import logic.usecase.SuggestNewKetoMealUseCase
import presentation.common.BaseUIController

class KetoDietMealHelperUI(
    private val suggestNewKetoMealUseCase: SuggestNewKetoMealUseCase
) : BaseUIController {
    override val id: Int = 7
    override val message: String = "7- Get one keto-friendly meal.."
    override fun start() {
        val alreadySuggestedIds: MutableSet<Int> = mutableSetOf()
        while (true) {
            try {
                val ketoMeal = suggestNewKetoMealUseCase.execute(alreadySuggestedIds.toSet())
                println("Name of keto meal : ${ketoMeal.name} \n and description of this keto : ${ketoMeal.description}")

                println("Enter yes if you like Keto meal to view it's details \n and no if you don't like it to suggest another Keto meal ")
                when ("yes") { // input
                    "yes" -> {
                        println(ketoMeal)
                        break
                    }

                    "no" -> {
                        println("lets try another one")
                        alreadySuggestedIds.add(ketoMeal.id)
                    }
                }
            } catch (exception: Exception) {
                println(exception.message)
                break
            }
        }
    }
}