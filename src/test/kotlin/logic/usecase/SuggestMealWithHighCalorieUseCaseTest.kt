package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Date

class SuggestMealWithHighCalorieUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var useCase: SuggestMealWithHighCalorieUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk(relaxed = true)
        useCase = SuggestMealWithHighCalorieUseCase(mealsRepository)
    }

    @Test
    fun `should return high calorie meal when is available`() {

        every { mealsRepository.getAllMeals() } returns listOf(
            createTestMeal(id = 1, calories = 450.0, description = "delicious toast"),
            createTestMeal(id = 2, calories = 250.0, description = "delicious toast"),
            createTestMeal(id = 3, calories = 850.0, description = "delicious toast")
        )

        val result = useCase.suggestRandomHighCalorieMeal(setOf())

        assertThat(result!!.nutrition.calories).isGreaterThan(700.0)
    }
}
