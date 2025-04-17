package dependencies

import logic.usecase.ExploreOtherCountriesFoodUseCase
import logic.usecase.GetAllMealsUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }

    single {
        ExploreOtherCountriesFoodUseCase(get())
    }
}