package dependencies

import org.koin.dsl.module
import presentation.features.*

val uiModule = module {
    single { GuessGameConsoleUi(get()) }
    single { MealIngredientsGameUI(get(), get(), get()) }
    single { SearchMealsByDateUI(get()) }
    single { EasyFoodSuggestionGameUI() }
    single { ExploreOtherCountriesFoodCultureUI() }
    single { GetHealthyFastFoodMealsUI() }
    single { GetIraqMealUI() }
    single { GymHelperUI() }
    single { ILovePotatoUI() }
    single { KetoDietMealHelperUI() }
    single { LargeItaliansMealsUI() }
    single { SeaFoodMealsUI() }
    single { SearchMealByNameUI() }
    single { SweetWithNoEggsUI() }
    single { SuggestMealWithHighCalorieUI(get()) }
}