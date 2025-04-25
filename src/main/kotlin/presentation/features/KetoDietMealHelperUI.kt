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
                println("Name of keto meal : ${ketoMeal.name} \n")
                if (ketoMeal.description != null) {
                    println("and description of this keto : ${ketoMeal.description}")
                }

                println("Enter (1) if you like Keto meal to view it's details \n and (0) if you don't like it to suggest another Keto meal: ")
                val option = readlnOrNull() ?: ""
                when (option) {
                    "1" -> {
                        println(ketoMeal)
                        break
                    }

                    "0" -> {
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