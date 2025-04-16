package logic.usecase.mealIngredientsGame

import logic.Repository.MealsRepository

class GetGameScoreUseCase(private val repository: MealsRepository) {

    operator fun invoke(): Int {
        return repository.getCorrectGuessedMealsNames().size * 1000
    }

}