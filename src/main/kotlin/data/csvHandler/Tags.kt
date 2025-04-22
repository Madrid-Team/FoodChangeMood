package data.csvHandler

object Tags {
    object FileConfig {
        const val FILE_NAME = "food.csv"
    }


    object GameScore {
        const val CORRECT_GUESS_POINTS = 1000
        const val EXTRA_INGREDIENT_MULTIPLIER = 10
        const val INCORRECT_INGREDIENT_NUMBER = 2
        const val MAX_CORRECT_GUESSES = 15
    }


    object MealCategories {
        const val SEAFOOD = "seafood"
        const val SWEETS = "sweet"
        const val EGG = "egg"
        const val POTATO = "potato"
        const val FOR_LARGE_GROUP = "for-large-groups"
        const val COUNTRY_MEAL_COUNT = 20
        const val RANDOM_MEALS_INCLUDE_POTATO = 10
    }

    object MealFilters {
        const val TOP_TEN_SUGGEST = 10
        const val MAX_PREPARED_TIME_FOR_EASY_FOOD = 30
        const val MAX_STEPS_COUNT = 6
        const val MAX_INGREDIENTS_COUNT = 5
        const val HIGH_CALORIES = 700
    }

    object HealthyMealCriteria {
        const val MAX_PREPARED_TIME_FOR_HEALTHY_FOOD = 15
        const val MAX_TOTAL_FAT = 20.0
        const val MAX_SATURATED_FAT = 5.0
        const val MAX_CARBOHYDRATES = 50.0
        const val TOP_HEALTHY_MEAL = 15
    }

    object GymMealParameters {
        const val CALORIES_MARGIN = 10
        const val PROTEIN_MARGIN = 10
    }

    object UserMessages {
        const val MESSAGE_MEALS_FOUND_FOR_DATE = "No meals found for date: %d"
        const val MESSAGE_NO_HEALTHY_FAST_FOOD = "There is no healthy fast food"
        const val MESSAGE_CORRECT_GUESS = "That's correct! You have guessed %d meals correctly!"
        const val MESSAGE_ALL_GUESSES_PASSED = "You have guessed all the meals correctly!"
        const val MESSAGE_WRONG_GUESS = "Ooh, you didn't guess the meal name"
        const val MESSAGE_NO_EASY_MEAL_SUGGEST = "There is no easy food suggestion"
        const val MESSAGE_NO_MEAL_SUGGEST = "There is no Meal Suggest!"
    }

    object Keto {
        const val PERCENTAGE = 100

        // nutrition value
        const val MAX_NUTRITION_CARBOHYDRATE = 10.0
        const val MIN_NUTRITION_PROTEIN = 20.0
        const val MAX_NUTRITION_PROTEIN = 50.0
        const val MIN_TOTAL_FAT = 30.0

        // Keto Percentage
        const val MAX_CARB_PERCENTAGE = 5
        const val MIN_FAT_PERCENTAGE = 70

        // calories per gram
        const val CARBS_PROTEIN_PER_GRAM = 4
        const val FAT_CALORIES_PER_GRAM = 9
    }
}



