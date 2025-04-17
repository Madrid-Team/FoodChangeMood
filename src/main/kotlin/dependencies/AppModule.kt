package dependencies

import data.csvHandler.MealsCsvParser
import data.csvHandler.MealsCsvReader
import data.csvHandler.Repository.MealsRepositoryImpl
import logic.Repository.MealsFilter
import logic.Repository.MealsRepository
import logic.usecase.HealthyFastFoodFilter
import org.koin.dsl.module
import presentation.FoodChangeMoodConsoleUI
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
        FoodChangeMoodConsoleUI(get(), get(), get())
    }
    single {
        HealthyFastFoodFilter()
    }
    single<MealsFilter> {
        HealthyFastFoodFilter()
    }
}