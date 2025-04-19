package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.StartGuessGameUseCase
import logic.usecase.GetMealsSuitableForGymUseCase
import org.koin.dsl.module
import presentation.GuessGameConsoleUi


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
    }
    single { StartGuessGameUseCase(get()) }
    single { GuessGameConsoleUi(get()) }

    single {
        GetMealsSuitableForGymUseCase(get())
    }

}