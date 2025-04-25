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

class GetHealthyMealsUITest {

    private lateinit var healthyMealsUseCase: GetHealthyMealsUseCase
    private lateinit var healthyMealsUI: GetHealthyMealsUI
    private lateinit var reader: ConsoleReader

    @BeforeEach
    fun setUp() {
        healthyMealsUseCase = mockk(relaxed = true)
        reader = mockk(relaxed = true)
        healthyMealsUI = GetHealthyMealsUI(healthyMealsUseCase,reader)
    }

    @Test
    fun `Should show Please enter a positive number When input is null`() {
        // Given
        every { reader.getUserInput() } returns null

        // When
        healthyMealsUI.start()

        // Then
        verifySequence {
            println("Enter your maximum count of healthy meals you want to proceed: ")
            println("Please enter a positive number.\n")
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
            println("Enter your maximum count of healthy meals you want to proceed: ")
            println("Please enter a positive number.\n")
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

}