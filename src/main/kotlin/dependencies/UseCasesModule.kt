package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.SuggestMealWithHighCalorieUseCase
import logic.usecase.GetMealsSuitableForGymUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }
    single { SuggestMealWithHighCalorieUseCase(get()) }

    single {
        GetMealsSuitableForGymUseCase(get())
    }

}