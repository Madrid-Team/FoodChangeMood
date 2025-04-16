package dependencies

import logic.usecase.GetAllIraqiMealsUseCase
import logic.usecase.GetAllMealsUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
        GetAllIraqiMealsUseCase(get())
    }

}