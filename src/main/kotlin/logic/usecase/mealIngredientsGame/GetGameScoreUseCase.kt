package logic.usecase.mealIngredientsGame

import data.csvHandler.Tags
import logic.Repository.MealsRepository

class GetGameScoreUseCase(private val repository: MealsRepository) {

    operator fun invoke(): Int {
        return repository.getCorrectGuessedMealsNames().size * Tags.GameScore.CORRECT_GUESS_POINTS
    }


}