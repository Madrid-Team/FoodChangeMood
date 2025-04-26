package logic.usecase

import createMeal
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetHealthyMealsUseCaseTest {

    private lateinit var mealRepository: MealsRepository
    private lateinit var getHealthyMealsUseCase: GetHealthyMealsUseCase

    @BeforeEach
    fun setUp() {
        mealRepository = mockk()
        getHealthyMealsUseCase = GetHealthyMealsUseCase(mealRepository)
    }

    /**
     *   healthy meals requirements
     * - minutes <= 15
     * - totalFat < 20
     * - saturatedFat < 5
     * - carbohydrates < 50
     */

    @Test
    fun `Should return maximum count of healthy meals When filtering meals`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 14, totalFat = 16.0, saturatedFat = 2.0, carbohydrates = 16.0), // Valid
            createMeal(minutes = 16, totalFat = 16.0, saturatedFat = 2.0, carbohydrates = 16.0), // Time long
            createMeal(minutes = 15, totalFat = 12.0, saturatedFat = 3.0, carbohydrates = 22.0), // Valid
            createMeal(minutes = 11, totalFat = 10.0, saturatedFat = 2.0, carbohydrates = 32.0), // Valid
        )

        // When
        val result = getHealthyMealsUseCase.execute(2)

        // Then
        assertEquals(2, result.size)
    }

    @Test
    fun `Should throw NoSuchElementException when meals list is empty`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf()

        // When & Then
        assertThrows<NoSuchElementException> { getHealthyMealsUseCase.execute(2) }
    }

    @Test
    fun `Should throw NoSuchElementException when no healthy meal exist`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 14, totalFat = 23.0, saturatedFat = 2.0, carbohydrates = 16.0), // High total fat
            createMeal(minutes = 16, totalFat = 16.0, saturatedFat = 2.0, carbohydrates = 16.0), // Time long
            createMeal(minutes = 15, totalFat = 12.0, saturatedFat = 7.0, carbohydrates = 22.0), // High saturatedFat
            createMeal(minutes = 11, totalFat = 10.0, saturatedFat = 2.0, carbohydrates = 52.0), // High carbohydrates
        )

        // When & Then
        assertThrows<NoSuchElementException> { getHealthyMealsUseCase.execute(2) }
    }

    @Test
    fun `Should return healthy meals sorted when filtering meals`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal(id = 1, minutes = 14, totalFat = 16.0, saturatedFat = 2.0, carbohydrates = 30.0), // Valid
            createMeal(id = 2, minutes = 4, totalFat = 8.0, saturatedFat = 2.0, carbohydrates = 12.0), // Valid
            createMeal(id = 3, minutes = 15, totalFat = 12.0, saturatedFat = 3.0, carbohydrates = 16.0), // Valid
            createMeal(id = 4, minutes = 11, totalFat = 5.0, saturatedFat = 1.0, carbohydrates = 8.0), // Valid
        )

        // When
        val result = getHealthyMealsUseCase.execute(4)

        val expected = listOf(
            createMeal(id = 4, minutes = 11, totalFat = 5.0, saturatedFat = 1.0, carbohydrates = 8.0),
            createMeal(id = 2, minutes = 4, totalFat = 8.0, saturatedFat = 2.0, carbohydrates = 12.0),
            createMeal(id = 3, minutes = 15, totalFat = 12.0, saturatedFat = 3.0, carbohydrates = 16.0),
            createMeal(id = 1, minutes = 14, totalFat = 16.0, saturatedFat = 2.0, carbohydrates = 30.0),
        )

        // Then
        assertTrue { result.zip(expected).all { (resultItem, expectedItem) -> resultItem.id == expectedItem.id } }
    }


    @Test
    fun `Should return available healthy meals up to count limit When filtering meals`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 14, totalFat = 16.0, saturatedFat = 2.0, carbohydrates = 16.0), // Valid
            createMeal(minutes = 16, totalFat = 16.0, saturatedFat = 2.0, carbohydrates = 16.0), // Time long
            createMeal(minutes = 15, totalFat = 12.0, saturatedFat = 3.0, carbohydrates = 22.0), // Valid
            createMeal(minutes = 22, totalFat = 10.0, saturatedFat = 2.0, carbohydrates = 32.0), // Time long
        )

        // When
        val result = getHealthyMealsUseCase.execute(3)

        // Then
        assertEquals(2, result.size)
    }
}