package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.SuggestKetoMealUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }
    single { SuggestKetoMealUseCase(get()) }

}