package dependencies

import logic.usecase.ExploreOtherCountriesFoodUseCase
import logic.usecase.GetAllMealsUseCase
import logic.usecase.MealSearchingUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }

    single {
        MealSearchingUseCase(get())
    }

    single {
        ExploreOtherCountriesFoodUseCase(get())
    }
}