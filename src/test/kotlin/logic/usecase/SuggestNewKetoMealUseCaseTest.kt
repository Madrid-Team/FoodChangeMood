package logic.usecase

import createMeal
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SuggestNewKetoMealUseCaseTest {
    private lateinit var mealRepository: MealsRepository
    private lateinit var suggestNewKetoMealUseCase: SuggestNewKetoMealUseCase

    @BeforeEach
    fun setUp() {
        mealRepository = mockk()
        suggestNewKetoMealUseCase = SuggestNewKetoMealUseCase(mealRepository)
    }

    /**
     * keto meal diet requirements
     * - carb < 10g per meal
     * - protein 20g-50g per meal
     * - fat 30g+ per meal
     * - sugar >= 5
     */

    @Test
    fun `Should return first valid meal When checking for keto meal conditions`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal(carbohydrates = 4.0, protein = 2.0, totalFat = 0.0, sugar = 13.0), // low protein and total fat
            createMeal(carbohydrates = 13.0, protein = 22.0, totalFat = 38.0, sugar = 6.5), // high carbohydrates
            createMeal(carbohydrates = 8.0, protein = 25.0, totalFat = 33.5, sugar = 6.0), // valid
            createMeal(carbohydrates = 2.0, protein = 25.0, totalFat = 34.0, sugar = 3.0), // low sugar
        )

        // When
        val result = suggestNewKetoMealUseCase.execute(setOf())

        // Then
        assertEquals(3, result.id)
    }

    @Test
    fun `Should throw NoSuchElementException when no valid keto meal found`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal(carbohydrates = 4.0, protein = 2.0, totalFat = 0.0, sugar = 13.0), // low protein and total fat
            createMeal(carbohydrates = 13.0, protein = 22.0, totalFat = 38.0, sugar = 6.5), // high carbohydrates
            createMeal(carbohydrates = 8.0, protein = 25.0, totalFat = 13.5, sugar = 6.0), // low total fat
            createMeal(carbohydrates = 2.0, protein = 25.0, totalFat = 34.0, sugar = 3.0), // low sugar
        )

        // When & Then
        assertThrows<NoSuchElementException> { suggestNewKetoMealUseCase.execute(setOf()) }
    }

    @Test
    fun `Should throw Exception when meals list is empty`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf()

        // When & Then
        assertThrows<Exception> { suggestNewKetoMealUseCase.execute(setOf()) }
    }

    @Test
    fun `Should throw NoSuchElementException when no valid keto meals available`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal(carbohydrates = 4.0, protein = 2.0, totalFat = 0.0, sugar = 13.0), // low protein and total fat
            createMeal(carbohydrates = 13.0, protein = 22.0, totalFat = 38.0, sugar = 6.5), // high carbohydrates
            createMeal(carbohydrates = 8.0, protein = 25.0, totalFat = 33.5, sugar = 6.0), // valid but taken before
            createMeal(carbohydrates = 2.0, protein = 25.0, totalFat = 34.0, sugar = 3.0), // low sugar
        )

        // When & Then
        assertThrows<NoSuchElementException> { suggestNewKetoMealUseCase.execute(setOf(3)) }
    }

}