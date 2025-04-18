package presentation

import dependencies.appModule
import dependencies.useCaseModule
import org.koin.core.context.startKoin

fun main() {


    startKoin {
        modules(appModule, useCaseModule)
    }


}


