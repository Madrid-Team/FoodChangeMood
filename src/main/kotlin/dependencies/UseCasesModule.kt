package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.GetKetoMealSuggestUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }
    single { GetKetoMealSuggestUseCase(get()) }

}