package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.StartGuessGameUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        GetAllMealsUseCase()
    }
    single { StartGuessGameUseCase(get()) }

}