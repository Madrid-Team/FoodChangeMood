package dependencies

import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.FoodChangeMoodConsoleUI
import presentation.common.BaseUIController
import presentation.common.ConsoleReader
import presentation.common.Reader
import presentation.features.*

val uiModule = module {
    single<Reader> { ConsoleReader() }

    single { GuessGameConsoleUi(get()) } bind BaseUIController::class
    single { MealIngredientsGameUI(get(), get(), get()) } bind BaseUIController::class
    single { SearchMealsByDateUI(get()) } bind BaseUIController::class
    single { EasyFoodSuggestionGameUI(get()) } bind BaseUIController::class
    single { ExploreOtherCountriesFoodCultureUI() } bind BaseUIController::class
    single { GetHealthyFastFoodMealsUI() } bind BaseUIController::class
    single { GetIraqMealUI() } bind BaseUIController::class
    single { GymHelperUI() } bind BaseUIController::class
    single { ILovePotatoUI() } bind BaseUIController::class
    single { KetoDietMealHelperUI() } bind BaseUIController::class
    single { LargeItaliansMealsUI() } bind BaseUIController::class
    single { SeaFoodMealsUI() } bind BaseUIController::class
    single { SearchMealByNameUI() } bind BaseUIController::class
    single { SweetWithNoEggsUI() } bind BaseUIController::class
    single { SuggestMealWithHighCalorieUI(get()) } bind BaseUIController::class

    single {
        FoodChangeMoodConsoleUI(
            controllers = getAll<BaseUIController>(),
            reader = get()
        )
    }
}