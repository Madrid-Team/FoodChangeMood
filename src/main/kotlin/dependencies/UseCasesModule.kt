package dependencies

import logic.usecase.ExploreOtherCountriesFoodUseCase
import logic.usecase.GetAllMealsUseCase
import logic.usecase.GetKetoMealSuggestUseCase
import logic.usecase.GetTenEasyFoodSuggestionUseCase
import logic.usecase.GetTopHealthyFastFoodUseCase
import logic.usecase.MealSearchingUseCase
import logic.usecase.StartGuessGameUseCase
import org.koin.dsl.module
import presentation.GuessGameConsoleUi


val useCaseModule = module {
    single {
        GetAllMealsUseCase(get())
    }
    single { GetKetoMealSuggestUseCase(get()) }
    single { StartGuessGameUseCase(get()) }
    single { GuessGameConsoleUi(get()) }
    single {
        GetTopHealthyFastFoodUseCase(get(), get())
    }
    single { GetTenEasyFoodSuggestionUseCase(get(), get()) }

    single {
        MealSearchingUseCase(get())
    }
    single {
        ExploreOtherCountriesFoodUseCase(get())
    }
}