package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class GetItalianFoodForLargeGroupsUseCase(mealsRepository: MealsRepository) {

    fun getItalianFoodForLargeGroups(mealsRepository: MealsRepository) : List<Meal> {
        val italianMealsForLargeGroups : List<Meal>
        return mealsRepository.getAllMeals().filter{meal -> isItalian(meal) && isForLargeGroups(meal)}



    }

        fun isForLargeGroups(meal : Meal) : Boolean{
           return meal.tags.contains("for-large-groups")}





    fun isItalian(meal : Meal) : Boolean {
           return meal.tags.contains(ITALIAN_KEYWORD) ||
            meal.ingredients.ingredients.contains(ITALIAN_KEYWORD) ||
                   (meal.description?.contains(ITALIAN_KEYWORD,ignoreCase = true) ?: false)  }




    companion object {
        const val ITALIAN_KEYWORD = "italian"

    }



}