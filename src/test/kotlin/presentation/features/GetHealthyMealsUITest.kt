package presentation.features

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.GetHealthyMealsUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.ConsoleReader

class GetHealthyMealsUITest {

    private lateinit var healthyMealsUseCase: GetHealthyMealsUseCase
    private lateinit var healthyMealsUI: GetHealthyMealsUI
    private lateinit var reader: ConsoleReader

    @BeforeEach
    fun setUp() {
        healthyMealsUseCase = mockk()
        healthyMealsUI = GetHealthyMealsUI(healthyMealsUseCase)
        reader = mockk()
    }

    @Test
    fun `Should show Please enter a positive number if input is null`() {
        // Given
        every { reader.getUserInput() } returns null

        // When
        healthyMealsUI.start()

        // Then
        verify { println("Please enter a positive number.\n") }
    }
}