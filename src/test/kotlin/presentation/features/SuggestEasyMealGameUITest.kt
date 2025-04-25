package presentation.features

import createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.SuggestEasyMealUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.ConsoleViewer

class SuggestEasyMealGameUITest {
    private lateinit var suggestEasyMealUseCase: SuggestEasyMealUseCase
    private lateinit var suggestEasyMealGameUI: SuggestEasyMealGameUI
    private lateinit var viewer: ConsoleViewer

    @BeforeEach
    fun setUp() {
        suggestEasyMealUseCase = mockk(relaxed = true)
        viewer = mockk(relaxed = true)
        suggestEasyMealGameUI = SuggestEasyMealGameUI(suggestEasyMealUseCase, viewer)
    }

    @Test
    fun `Should show 10 meals or less when suggestEasyMealUseCase returns meals`() {
        val meals = listOf(
            createMeal(minutes = 14, stepsCount = 4, ingredientsCount = 2), // Valid
            createMeal(minutes = 12, stepsCount = 1, ingredientsCount = 3), // Valid
            createMeal(minutes = 22, stepsCount = 3, ingredientsCount = 4), // Valid
        )
        // Given
        every { suggestEasyMealUseCase.execute(10) } returns meals

        // When
        suggestEasyMealGameUI.start()

        // Then
        verify { suggestEasyMealUseCase.execute(10) }
        meals.forEach { meal ->
            viewer.show(meal.toString())
        }
    }

    @Test
    fun `Should show There is no easy meals When filter is return empty`() {
        // Given
        every { suggestEasyMealUseCase.execute(10) } throws NoSuchElementException()

        // When
        suggestEasyMealGameUI.start()

        // Then
        verify { suggestEasyMealUseCase.execute(10) }
        verify { viewer.show("There is no easy food suggestion") }
    }

}