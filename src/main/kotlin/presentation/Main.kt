package presentation

import dependencies.appModule
import dependencies.useCaseModule
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin


fun main() {
    startKoin {
        modules(appModule, useCaseModule)
    }
    val consoleUi: FoodChangeMoodConsoleUI = getKoin().get()
    consoleUi.start()
}



