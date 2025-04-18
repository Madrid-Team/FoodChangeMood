package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.ShowRandomMealsIncludePotatoesUseCase
import logic.usecase.*
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
}