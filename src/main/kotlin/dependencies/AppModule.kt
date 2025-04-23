package dependencies

import data.csvHandler.MealsCsvParser
import data.csvHandler.MealsCsvReader
import data.csvHandler.repository.MealsRepositoryImpl
import data.csvHandler.Tags
import logic.Repository.MealsRepository
import org.koin.dsl.module
import java.io.File

val appModule = module {

    single {
        File(Tags.FileConfig.FILE_NAME)
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
}