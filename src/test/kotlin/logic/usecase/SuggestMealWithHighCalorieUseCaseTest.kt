package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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

    @Test
    fun `should return null when all high calorie meals are already suggested`() {
        every { mealsRepository.getAllMeals() } returns  listOf(
            createTestMeal(id = 1, calories = 800.0, description = "delicious toast"),
            createTestMeal(id = 2, calories = 750.0, description = "delicious toast")
        )

        val result = useCase.suggestRandomHighCalorieMeal(alreadySuggested = setOf(1, 2))

        assertThat(result).isNull()
    }

    @Test
    fun `should skip meals when description is null`() {
        every { mealsRepository.getAllMeals() } returns listOf(
            createTestMeal(id = 1, calories = 800.0, description = null),
            createTestMeal(id = 2, calories = 800.0, description = "delicious toast")
        )

        val result = useCase.suggestRandomHighCalorieMeal(alreadySuggested = setOf())

        assertThat(result!!.description).isNotNull()
    }

}
