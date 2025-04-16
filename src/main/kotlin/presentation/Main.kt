package presentation

import dependencies.appModule
import dependencies.useCaseModule
import logic.usecase.StartGuessGameUseCase
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin

fun main() {





    startKoin {
        modules(appModule, useCaseModule)
    }
    val game: StartGuessGameUseCase = getKoin().get()

        game.startGuessGame{ readlnOrNull()?.toIntOrNull() ?: 0}



}