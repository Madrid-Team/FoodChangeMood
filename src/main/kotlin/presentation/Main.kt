package presentation

import dependencies.appModule
import dependencies.useCaseModule
import logic.usecase.GetAllMealsUseCase
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(appModule, useCaseModule)
    }

    val consoleUI = FoodChangeMoodConsoleUI(getAllMealsUseCase = GetAllMealsUseCase())
    consoleUI.start()
}