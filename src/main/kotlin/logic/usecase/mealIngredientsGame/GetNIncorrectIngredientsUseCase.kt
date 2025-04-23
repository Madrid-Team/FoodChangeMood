package logic.usecase.mealIngredientsGame

import data.csvHandler.Tags.GameScore.EXTRA_INGREDIENT_MULTIPLIER
import data.models.Meal

class GetNIncorrectIngredientsUseCase {

    operator fun invoke(
        allMeals: List<Meal>,
        correctIngredient: String,
        incorrectIngredientsNumber: Int
    ): List<String> {
        val incorrectIngredients = allMeals
            .take(incorrectIngredientsNumber * EXTRA_INGREDIENT_MULTIPLIER)
            .flatMap { it.ingredients.ingredients }
            .distinct()
            .filter { it != correctIngredient }
            .shuffled()
            .take(incorrectIngredientsNumber)
        if (incorrectIngredients.size < incorrectIngredientsNumber) {
            return invoke(allMeals, correctIngredient, incorrectIngredientsNumber)
        }

        return incorrectIngredients
    }
}