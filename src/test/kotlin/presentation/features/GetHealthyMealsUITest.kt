package presentation.features

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import logic.usecase.GetHealthyMealsUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import presentation.common.ConsoleReader
import presentation.common.ConsoleViewer

class GetHealthyMealsUITest {

    private lateinit var healthyMealsUseCase: GetHealthyMealsUseCase
    private lateinit var healthyMealsUI: GetHealthyMealsUI
    private lateinit var reader: ConsoleReader
    private lateinit var viewer: ConsoleViewer

    @BeforeEach
    fun setUp() {
        healthyMealsUseCase = mockk(relaxed = true)
        reader = mockk(relaxed = true)
        viewer = mockk(relaxed = true)
        healthyMealsUI = GetHealthyMealsUI(healthyMealsUseCase, reader, viewer)
    }

    @Test
    fun `Should show Please enter a positive number When input is null`() {
        // Given
        every { reader.getUserInput() } returns null

        // When
        healthyMealsUI.start()

        // Then
        verifySequence {
            viewer.show("Enter your maximum count of healthy meals you want to proceed: ")
            viewer.show("Please enter a positive number.\n")
        }
    }

    @ParameterizedTest
    @CsvSource("0", "-5", "c")
    fun `Should show Please enter a positive number When input is less than or equal to 0`(input: String) {
        // Given
        every { reader.getUserInput() } returns input

        // When
        healthyMealsUI.start()

        // Then
        verifySequence {
            viewer.show("Enter your maximum count of healthy meals you want to proceed: ")
            viewer.show("Please enter a positive number.\n")
        }
    }

    @Test
    fun `Should execute healthy meals use case When input is positive number`() {
        // Given
        every { reader.getUserInput() } returns "2"

        // When
        healthyMealsUI.start()

        // Then
        verify { healthyMealsUseCase.execute(2) }
    }

    @Test
    fun `Should show There is no healthy meals When filter is return empty`() {
        // Given
        every { reader.getUserInput() } returns "2"
        every { healthyMealsUseCase.execute(2) } throws NoSuchElementException("There is no healthy meals")

        // When
        healthyMealsUI.start()


        // Then
        verify { healthyMealsUseCase.execute(2) }
        verify { viewer.show("There is no healthy meals") }
    }

}