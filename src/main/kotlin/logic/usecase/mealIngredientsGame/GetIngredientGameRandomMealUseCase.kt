package logic.usecase.mealIngredientsGame

import data.csvHandler.Tags.GameScore.INCORRECT_INGREDIENT_NUMBER
import data.models.IngredientGameData
import logic.Repository.MealsRepository

class GetIngredientGameRandomMealUseCase(
    private val getNIncorrectIngredientsUseCase: GetNIncorrectIngredientsUseCase,
    private val mealsRepository: MealsRepository
) {

    operator fun invoke(): IngredientGameData {
        val allMeals = mealsRepository.getAllMeals()

        var selectedMeal = allMeals.random()
        while (selectedMeal.ingredients.ingredients.isEmpty()) {
            selectedMeal = allMeals.random()
        }

        val mealIngredients = selectedMeal.ingredients.ingredients

        val correctIngredient = mealIngredients.random()

        val incorrectIngredients = getNIncorrectIngredientsUseCase.invoke(
            allMeals = allMeals,
            correctIngredient = correctIngredient,
            incorrectIngredientsNumber = INCORRECT_INGREDIENT_NUMBER
        )

        val options = (incorrectIngredients + listOf(correctIngredient)).shuffled()

        return IngredientGameData(
            mealName = selectedMeal.name,
            options = options,
            correctAnswer = correctIngredient
        )
    }
}