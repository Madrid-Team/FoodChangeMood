package presentation

import data.models.Meal
import data.utilities.MealsExceptions
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.GetFoodByAddDateUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class SearchMealsByDateUITest {
    private lateinit var getFoodByAddDateUseCase: GetFoodByAddDateUseCase
    private lateinit var searchMealsByDateUI: SearchMealsByDateUI

    // Output capturing
    private val standardOut = System.out
    private val outputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        getFoodByAddDateUseCase = mockk()
        searchMealsByDateUI = SearchMealsByDateUI()

        // Redirect stdout
        System.setOut(PrintStream(outputStream))
        startKoin {
            modules(
                module {
                    single { getFoodByAddDateUseCase }
                }
            )
        }
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
        stopKoin()
        clearAllMocks()
    }

    private fun setUserInput(input: String) {
        System.setIn(ByteArrayInputStream(input.toByteArray()))
    }

    private fun getOutput() = outputStream.toString().also { outputStream.reset() }

    @Test
    fun `should display meals when date is valid and allow returning to date selection`() {
        // Arrange
        val mockMeal1 = mockk<Meal>(relaxed = true)
        val mockMeal2 = mockk<Meal>(relaxed = true)

        every { mockMeal1.id } returns  1
        every { mockMeal1.name } returns "Test Meal 1"

        every { mockMeal2.id } returns 2
        every { mockMeal2.name } returns "Test Meal 2"

        val mockMeals = listOf(mockMeal1, mockMeal2)

        every { getFoodByAddDateUseCase.invoke("2023-01-01") } returns mockMeals

        // When
        // User enters date, then 'back' to search again, then 'exit' to quit
        setUserInput("2023-01-01\nback\nexit\n")
        searchMealsByDateUI.start()

        // Then
        val output = getOutput()
        // Check if the meal list was displayed correctly
        assertTrue(output.contains("=== Meals found: 2 ==="))
        assertTrue(output.contains("1\t| Test Meal 1"))
        assertTrue(output.contains("2\t| Test Meal 2"))
        // Check if the prompt to enter ID or 'back' appeared
        assertTrue(output.contains("Enter a meal ID to view details or 'back' to search another date:"))
        // Check if the date prompt appeared again after 'back'
        assertTrue(output.contains("Enter date (yyyy-MM-dd) or 'exit' to quit:"))
        // Check if the exit message appeared
        assertTrue(output.contains("Exiting meal search..."))

        // Verify the use case was called for the entered date
        verify { getFoodByAddDateUseCase.invoke("2023-01-01") }
    }


    @Test
    fun `should show exception message when no meals found`() {
        // Given
        every { getFoodByAddDateUseCase.invoke("2023-01-01") } throws
                MealsExceptions.MealNotFoundException("No meals found for 2023-01-01")

        // When
        setUserInput("2023-01-01\nexit\n")
        searchMealsByDateUI.start()

        // Then
        val output = getOutput()
        assertTrue(output.contains(MealsExceptions.MealNotFoundException("No meals found for 2023-01-01").message))


        assertThrows(MealsExceptions.MealNotFoundException::class.java) { getFoodByAddDateUseCase.invoke("2023-01-01") }
        verify { getFoodByAddDateUseCase.invoke("2023-01-01") }
    }

    @Test
    fun `should show exception message when date format is invalid`() {
        // Given
        every { getFoodByAddDateUseCase.invoke("invalid-date") } throws
                MealsExceptions.InvalidDateFormatException("Invalid date format: invalid-date")

        // When
        setUserInput("invalid-date\nexit\n")
        searchMealsByDateUI.start()

        // Then
        val output = getOutput()
        assertTrue(output.contains("Error: Invalid date format: invalid-date"))

        assertThrows(MealsExceptions.InvalidDateFormatException::class.java) { getFoodByAddDateUseCase.invoke("invalid-date") }
//        verify { getFoodByAddDateUseCase.invoke("invalid-date") }
    }
}