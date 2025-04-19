package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.GetKetoMealSuggestUseCase
import logic.usecase.GetMealsSuitableForGymUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }
    single { GetKetoMealSuggestUseCase(get()) }

    single {
        GetMealsSuitableForGymUseCase(get())
    }

}