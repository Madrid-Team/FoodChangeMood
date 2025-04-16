package presentation

import data.csvHandler.ColumnIndex
import data.csvHandler.MealsCsvParser
import dependencies.appModule
import dependencies.useCaseModule
import org.apache.commons.csv.CSVParser
import org.koin.core.context.startKoin
import java.io.File
import java.io.FileReader

fun main() {


    startKoin {
        modules(appModule, useCaseModule)
    }


}


