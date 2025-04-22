package presentation

import dependencies.appModule
import dependencies.useCaseModule
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin
import presentation.features.GuessGameConsoleUi


fun main() {


    startKoin {
        modules(appModule, useCaseModule)
    }

    val guessGameConsolUi: GuessGameConsoleUi = getKoin().get()
    guessGameConsolUi.startGame()


    val startTime = System.currentTimeMillis()
    val consoleUi: FoodChangeMoodConsoleUI = getKoin().get()
    val endTime = System.currentTimeMillis()
    println("Time to read meals from csv file: ${(endTime - startTime) / 1000} second")
    consoleUi.start()
}

