package dependencies

import logic.usecase.GetAllMealsUseCase
import logic.usecase.SuggestEasyMealUseCase
import logic.usecase.GetHealthyFoodUseCase
import logic.usecase.*
import logic.usecase.mealIngredientsGame.GetGameScoreUseCase
import logic.usecase.mealIngredientsGame.GetIngredientGameRandomMealUseCase
import logic.usecase.mealIngredientsGame.GetNIncorrectIngredientsUseCase
import logic.usecase.mealIngredientsGame.MakeGuessUseCase
import org.koin.dsl.module
import presentation.GuessGameConsoleUi


val useCaseModule = module {

    single {
        GetAllMealsUseCase(get())
        GetAllSeafoodMealsUseCase(get())
    }
    single { GetKetoMealSuggestUseCase(get()) }
    single { StartGuessGameUseCase(get()) }

    single {
        GetSweetsWithNoEggsUseCase(get())
    }
    single {
        ShowRandomMealsIncludePotatoesUseCase(get())
    }
    single { SuggestMealWithHighCalorieUseCase(get()) }
    single {

        GetAllIraqiMealsUseCase(get())
    }

    single {
        GetMealsSuitableForGymUseCase(get())
    }
    single {
        MealSearchingByNameUseCase(get())
    }
    single {
        ExploreOtherCountriesFoodUseCase(get())
    }
    single { GetItalianFoodForLargeGroupsUseCase(get()) }
    single { SuggestMealWithHighCalorieUseCase(get()) }
    single {
        GetIngredientGameRandomMealUseCase(get(), get())
    }
    single {
        GetNIncorrectIngredientsUseCase()
    }
    single {
        MakeGuessUseCase(get())
    }
    single {
        GetGameScoreUseCase(get())
    }
    single { GetAllMealsUseCase(get()) }
    single { GetFoodByAddDateUseCase(get()) }
    single {
        SuggestEasyMealUseCase(get())
    }
    single {
        GetHealthyMealsUseCase(get())
    }
    single {
        GetSweetsWithNoEggsUseCase(get())
    }
    single { StartGuessGameUseCase(get()) }
    single { GuessGameConsoleUi(get()) }
    single { SuggestMealWithHighCalorieUseCase(get()) }
    single { GetKetoMealSuggestUseCase(get()) }

    single {
        GetMealsSuitableForGymUseCase(get())
    }

}