package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.GetTopHealthyFastFoodUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }
    single {
        GetTopHealthyFastFoodUseCase(get(),get())
    }

}