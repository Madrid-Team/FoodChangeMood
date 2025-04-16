package logic.usecase.mealIngredientsGame

import data.models.IngredientGameData
import logic.usecase.GetAllMealsUseCase

class GetIngredientGameRandomMealUseCase constructor(
    private val getNIncorrectIngredientsUseCase: GetNIncorrectIngredientsUseCase,
) {

    operator fun invoke(): IngredientGameData {
        val allMeals = GetAllMealsUseCase().invoke()

        var selectedMeal = allMeals.random()
        while (selectedMeal.ingredients.ingredients.isEmpty()) {
            selectedMeal = allMeals.random()
        }

        val mealIngredients = selectedMeal.ingredients.ingredients

        val correctIngredient = mealIngredients.random()


        val incorrectIngredients =
            getNIncorrectIngredientsUseCase.invoke(
                allMeals = allMeals,
                correctIngredient = correctIngredient,
                incorrectIngredientsNumber = 2
            )


        val options = (incorrectIngredients + correctIngredient).shuffled()

        return IngredientGameData(
            mealName = selectedMeal.name,
            options = options,
            correctAnswer = correctIngredient
        )
    }
}