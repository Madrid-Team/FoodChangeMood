package presentation

import dependencies.appModule
import dependencies.useCaseModule
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin


fun main() {
    println("Main function started")
    startKoin {
        println("Starting app...")

        modules(appModule, useCaseModule)
    }
    println("Starting app...")
    val consoleUi: FoodChangeMoodConsoleUI = getKoin().get()
    println("Got consoleUI")
    consoleUi.start()

}