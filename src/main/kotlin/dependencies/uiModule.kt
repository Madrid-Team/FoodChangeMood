package dependencies

import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.FoodChangeMoodConsoleUI
import presentation.common.*
import presentation.features.*

val uiModule = module {
    single<Reader> { ConsoleReader() }
    single<Viewer> { ConsoleViewer() }

    single { GuessGameConsoleUi(get(), get(), get()) } bind BaseUIController::class
    single { MealIngredientsGameUI(get(), get(), get(), get(), get()) } bind BaseUIController::class
    single { SearchMealsByDateUI(get(), get(), get()) } bind BaseUIController::class
    single { SuggestEasyMealGameUI(get(), get()) } bind BaseUIController::class
    single { ExploreOtherCountriesFoodCultureUI(get(), get(), get()) } bind BaseUIController::class
    single { GetHealthyMealsUI(get(), get(), get()) } bind BaseUIController::class
    single { GetIraqMealUI(get()) } bind BaseUIController::class
    single { GymHelperUI(get(), get(), get()) } bind BaseUIController::class
    single { ILovePotatoUI(get(), get()) } bind BaseUIController::class
    single { KetoDietMealHelperUI(get(), get(), get()) } bind BaseUIController::class
    single { LargeItaliansMealsUI(get(), get()) } bind BaseUIController::class
    single { SeaFoodMealsUI(get(), get()) } bind BaseUIController::class
    single { SearchMealByNameUI(get(), get(), get()) } bind BaseUIController::class
    single { SweetWithNoEggsUI(get(), get(), get()) } bind BaseUIController::class
    single { SuggestMealWithHighCalorieUI(get(), get(), get()) } bind BaseUIController::class

    single {
        FoodChangeMoodConsoleUI(
            controllers = getAll<BaseUIController>(),
            reader = get(),
            viewer = get(),
        )
    }
}