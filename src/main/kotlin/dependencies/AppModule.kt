package dependencies

import data.csvHandler.MealsCsvParser
import data.csvHandler.MealsCsvReader
import data.csvHandler.Repository.MealsRepositoryImpl
import logic.Repository.MealsRepository
import org.koin.dsl.module
import presentation.FoodChangeMoodConsoleUI
import presentation.GuessGameConsoleUi
import presentation.MealIngredientsGameUI
import presentation.SearchMealsByDateUI
import java.io.File

val appModule = module {

    single {
        File("food.csv")
    }
    single {
        MealsCsvReader(get())
    }
    single {
        MealsCsvParser()
    }
    single<MealsRepository> {
        MealsRepositoryImpl(get(), get())
    }

    single {
        FoodChangeMoodConsoleUI(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single {
        GuessGameConsoleUi(get())
    }
    single { MealIngredientsGameUI() }
    single { SearchMealsByDateUI() }

}