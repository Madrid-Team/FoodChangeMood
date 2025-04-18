package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.mealIngredientsGame.GetGameScoreUseCase
import logic.usecase.mealIngredientsGame.GetIngredientGameRandomMealUseCase
import logic.usecase.mealIngredientsGame.GetNIncorrectIngredientsUseCase
import logic.usecase.mealIngredientsGame.MakeGuessUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }
    single {
        GetIngredientGameRandomMealUseCase(get())
    }
    single {
        GetNIncorrectIngredientsUseCase()
    }
    single {
        MakeGuessUseCase(get())
    }
    single {
        GetGameScoreUseCase(get())
    }
}