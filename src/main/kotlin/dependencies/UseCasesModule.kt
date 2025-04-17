package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.GetHealthyFoodUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }
    single {
        GetHealthyFoodUseCase(get())
    }

}