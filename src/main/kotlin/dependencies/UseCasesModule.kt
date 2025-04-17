package dependencies

import logic.usecase.ExploreOtherCountriesFoodUseCase
import logic.usecase.GetAllMealsUseCase
import logic.usecase.GetTenEasyFoodSuggestionUseCase
import logic.usecase.GetTopHealthyFastFoodUseCase
import logic.usecase.MealSearchingUseCase
import org.koin.dsl.module


val useCaseModule = module {
    single {
        GetAllMealsUseCase(get())
    }
    single {
        GetTopHealthyFastFoodUseCase(get(),get())
    }
    single { GetTenEasyFoodSuggestionUseCase(get(),get()) }

    single {
        MealSearchingUseCase(get())
    }

    single {
        ExploreOtherCountriesFoodUseCase(get())
    }
}