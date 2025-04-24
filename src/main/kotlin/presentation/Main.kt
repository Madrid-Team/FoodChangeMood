package presentation

import dependencies.appModule
import dependencies.useCaseModule
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin


fun main() {
    startKoin {
        modules(appModule, useCaseModule)
    }

    val startTime = System.currentTimeMillis()
    val consoleUi: FoodChangeMoodConsoleUI = getKoin().get()
    val endTime = System.currentTimeMillis()
    println("Time to read meals from csv file: ${(endTime - startTime) / 1000} second")
    consoleUi.start()
}

