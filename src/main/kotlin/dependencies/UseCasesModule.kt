package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.GetAllSeafoodMealsUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
        GetAllSeafoodMealsUseCase(get())
    }

}