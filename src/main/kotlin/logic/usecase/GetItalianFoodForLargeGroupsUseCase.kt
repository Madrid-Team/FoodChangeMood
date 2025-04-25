package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetItalianFoodForLargeGroupsUseCase(private val mealsRepository: MealsRepository) {

    fun getItalianFoodForLargeGroups(): List<Meal> {
        if (mealsRepository.getAllMeals().isEmpty()) throw Exception("the Meal list is empty")

        val resultMeals : List<Meal> = mealsRepository.getAllMeals().filter { meal -> isItalianForLargeGroups(meal) }
        if (resultMeals.isEmpty())
            throw Exception("No italian meals for large groups found ")
        else return resultMeals

    }

    fun isItalianForLargeGroups(meal: Meal) : Boolean{
        return isItalian(meal) && isForLargeGroups(meal)
    }


    fun isForLargeGroups(meal: Meal): Boolean {
        return meal.tags.any { it.equals(FOR_LARGE_GROUPS , ignoreCase = true) }
    }


    fun isItalian(meal: Meal): Boolean {
        return meal.tags.contains(ITALIAN_KEYWORD) ||
                meal.ingredients.ingredients.contains(ITALIAN_KEYWORD) ||
                (meal.description?.contains(ITALIAN_KEYWORD, ignoreCase = true) ?: false)
    }


    companion object {
        const val ITALIAN_KEYWORD = "italian"
        const val FOR_LARGE_GROUPS = "for-large-groups"

    }


}