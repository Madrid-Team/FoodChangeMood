package presentation

import data.csvHandler.ColumnIndex
import data.csvHandler.MealsCsvParser
import dependencies.appModule
import dependencies.useCaseModule
import org.apache.commons.csv.CSVParser
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin

fun main() {
    startKoin {
        modules(appModule, useCaseModule)
    }
    val consoleUI: FoodChangeMoodConsoleUI = getKoin().get()
    consoleUI.start()
}