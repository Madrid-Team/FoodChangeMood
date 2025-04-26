package logic.usecase

import createMeal
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class SuggestEasyMealUseCaseTest {
    private lateinit var mealRepository: MealsRepository
    private lateinit var suggestEasyMealUseCase: SuggestEasyMealUseCase

    @BeforeEach
    fun setUp() {
        mealRepository = mockk()
        suggestEasyMealUseCase = SuggestEasyMealUseCase(mealRepository)
    }

    /**
     *   easy meals requirements
     * - minutes <= 30
     * - stepsCount <= 6
     * - ingredientsCount <= 5
     */

    @Test
    fun `Should return ten easy meals or less When filtering meals`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 14, stepsCount = 4, ingredientsCount = 2), // Valid
            createMeal(minutes = 36, stepsCount = 4, ingredientsCount = 2), // Time long
            createMeal(minutes = 14, stepsCount = 7, ingredientsCount = 2), // Steps long
            createMeal(minutes = 14, stepsCount = 4, ingredientsCount = 2), // Valid
        )

        // When
        val result = suggestEasyMealUseCase.execute(10)

        // Then
        assertEquals(2, result.size)
    }

    @Test
    fun `Should throw NoSuchElementException when meals list is empty`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf()

        // When & Then
        assertThrows<NoSuchElementException> { suggestEasyMealUseCase.execute(10) }
    }



}