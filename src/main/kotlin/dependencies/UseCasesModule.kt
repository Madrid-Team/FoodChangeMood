package dependencies

import logic.usecase.GetAllIraqiMealsUseCase
import logic.usecase.GetAllMealsUseCase
import logic.usecase.ShowRandomMealsIncludePotatoesUseCase
import logic.usecase.*
import logic.usecase.mealIngredientsGame.GetGameScoreUseCase
import logic.usecase.mealIngredientsGame.GetIngredientGameRandomMealUseCase
import logic.usecase.mealIngredientsGame.GetNIncorrectIngredientsUseCase
import logic.usecase.mealIngredientsGame.MakeGuessUseCase
import logic.usecase.GetFoodByAddDateUseCase
import org.koin.dsl.module
import presentation.GuessGameConsoleUi

val useCaseModule = module {
    single {
        GetAllMealsUseCase(get())
        GetAllSeafoodMealsUseCase(get())
    }
    single { GetKetoMealSuggestUseCase(get()) }
    single { StartGuessGameUseCase(get()) }
    single { GuessGameConsoleUi(get()) }
    single {
        GetTopHealthyFastFoodUseCase(get(), get())
    }
    single { GetTenEasyFoodSuggestionUseCase(get(), get()) }

    single {
        GetSweetsWithNoEggsUseCase(get())
    }
    single {
        ShowRandomMealsIncludePotatoesUseCase(get())
    }
    single {

        GetAllIraqiMealsUseCase(get())
    }

    single {
        GetMealsSuitableForGymUseCase(get())
    }
    single {
        MealSearchingUseCase(get())
    }
    single {
        ExploreOtherCountriesFoodUseCase(get())
    }
    single { GetItalianFoodForLargeGroupsUseCase(get()) }
    single { SuggestMealWithHighCalorieUseCase(get()) }
    single {
        GetIngredientGameRandomMealUseCase(get(), get())
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
    single { GetAllMealsUseCase(get()) }
    single { GetFoodByAddDateUseCase(get()) }
}