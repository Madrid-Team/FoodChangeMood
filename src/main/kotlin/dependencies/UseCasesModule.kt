package dependencies

import logic.Repository.MealsFilter
import logic.usecase.GetAllMealsUseCase
import logic.usecase.GetEasyFoodSuggestionUseCase
import logic.usecase.GetHealthyFoodUseCase
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

        GetTopHealthyFastFoodUseCase(get(), get())
    single {
        GetEasyFoodSuggestionUseCase(get())
    }
    single { GetTenEasyFoodSuggestionUseCase(get(), get()) }
    single<MealsFilter> {
        GetHealthyFoodUseCase(get())
        GetEasyFoodSuggestionUseCase(get())
    }
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