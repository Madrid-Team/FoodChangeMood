package logic.usecase.mealIngredientsGame

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
            correctIngredients = mealIngredients,
            incorrectIngredientsNumber = 2
        )

        val options = (incorrectIngredients + listOf(correctIngredient)).shuffled()

        return IngredientGameData(
            meal = selectedMeal,
            options = options,
            correctAnswer = correctIngredient
        )
    }
}