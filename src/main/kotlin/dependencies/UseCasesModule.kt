package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.MealSearchingByNameUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase()
    }

    single {
        MealSearchingByNameUseCase(get())
    }

}