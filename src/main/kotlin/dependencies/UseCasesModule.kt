package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.MealSearchingUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase()
    }

    single {
        MealSearchingUseCase(get())
    }

}