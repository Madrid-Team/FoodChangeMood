package presentation

import dependencies.appModule
import dependencies.useCaseModule
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin

fun main() {


    startKoin {
        modules(appModule, useCaseModule)
    }

    val guessGameConsolUi: GuessGameConsoleUi = getKoin().get()
    guessGameConsolUi.startGame()



}



