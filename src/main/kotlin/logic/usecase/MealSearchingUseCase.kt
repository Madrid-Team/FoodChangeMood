package logic.usecase

import data.models.Meal
import logic.Repository.MealsRepository

class MealSearchingUseCase(
    private val mealsRepository: MealsRepository
) {
    fun mealSearchingByName(mealNameByUser: String): List<Meal> {
        return mealsRepository.getAllMeals().filter { meal ->
            kmpSearch(realMealName = meal.name, mealNameByUser = mealNameByUser)
        }
    }

    private fun kmpSearch(realMealName: String, mealNameByUser: String): Boolean {
        val realMealNameLowerCase = realMealName.lowercase()
        val mealNameByUserLowerCase = mealNameByUser.lowercase()

        if (mealNameByUser.isEmpty()) return true

        val lps = buildLPS(mealNameByUserLowerCase)
        var i = 0
        var j = 0

        while (i < realMealNameLowerCase.length) {
            if (mealNameByUserLowerCase[j] == realMealNameLowerCase[i]) {
                i++
                j++
            }

            if (j == mealNameByUserLowerCase.length) {
                return true
            } else if (i < realMealNameLowerCase.length && mealNameByUserLowerCase[j] != realMealNameLowerCase[i]) {
                if (j != 0) {
                    j = lps[j - 1]
                } else {
                    i++
                }
            }
        }
        return false
    }

    private fun buildLPS(mealNameByUser: String): IntArray {
        val lps = IntArray(mealNameByUser.length)
        var length = 0
        var i = 1

        while (i < mealNameByUser.length) {
            if (mealNameByUser[i] == mealNameByUser[length]) {
                length++
                lps[i] = length
                i++
            } else {
                if (length != 0) {
                    length = lps[length - 1]
                } else {
                    lps[i] = 0
                    i++
                }
            }
        }
        return lps
    }
}