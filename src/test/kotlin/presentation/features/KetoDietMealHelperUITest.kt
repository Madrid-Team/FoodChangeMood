package presentation.features

import createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.SuggestNewKetoMealUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.ConsoleReader
import presentation.common.ConsoleViewer

class KetoDietMealHelperUITest {

    private lateinit var suggestNewKetoMealUseCase: SuggestNewKetoMealUseCase
    private lateinit var reader: ConsoleReader
    private lateinit var viewer: ConsoleViewer
    private lateinit var ketoDietMealUI: KetoDietMealHelperUI

    @BeforeEach
    fun setUp() {
        suggestNewKetoMealUseCase = mockk(relaxed = true)
        reader = mockk(relaxed = true)
        viewer = mockk(relaxed = true)
        ketoDietMealUI = KetoDietMealHelperUI(suggestNewKetoMealUseCase, reader, viewer)
    }

    @Test
    fun `Should display meal details When user options 1`() {
        // Given
        val meal = createMeal(
            name = "a bit different  breakfast pizza",
            id = 1,
            minutes = 15,
            stepsCount = 5,
            description = "this warms well in the microwave for those late risers"
        )
        every { suggestNewKetoMealUseCase.execute(setOf()) } returns meal
        every { reader.getUserInput() } returns "1"

        // When
        ketoDietMealUI.start()

        // Then
        verify {
            suggestNewKetoMealUseCase.execute(setOf())
            viewer.show("Name of keto meal : a bit different  breakfast pizza \nDescription of this keto meal : this warms well in the microwave for those late risers")
            viewer.show("Enter (1) if you like Keto meal to view it's details \n and (0) if you don't like it to suggest another Keto meal: ")
            viewer.show(meal.toString())
        }
    }


}