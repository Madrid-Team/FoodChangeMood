package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.ShowRandomMealsIncludePotatoesUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }
    single {
        ShowRandomMealsIncludePotatoesUseCase(get())
    }
}