package presentation.features

import com.google.common.truth.Truth
import data.models.SeafoodMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.GetAllSeafoodMealsUseCase
import org.junit.jupiter.api.BeforeEach
import presentation.common.Viewer
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test

class SeaFoodMealsUITest {
    private val getAllSeafoodMealsUseCase: GetAllSeafoodMealsUseCase = mockk(relaxed = true)
    private lateinit var seaFoodMealsUI: SeaFoodMealsUI
    private val outputStreamCaptor = ByteArrayOutputStream()
    private val viewer: Viewer = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        seaFoodMealsUI = SeaFoodMealsUI(getAllSeafoodMealsUseCase, viewer)
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun `controller properties should have correct values`() {
        // Given
        val mealUiId = 14
        val mealUiMessage = "14- You will get a list of all seafood meals sorted by protein content," +
                "from highest to lowest."
        // Then
        Truth.assertThat(seaFoodMealsUI.id).isEqualTo(mealUiId)
        Truth.assertThat(seaFoodMealsUI.message).isEqualTo(mealUiMessage)
    }

    @Test
    fun `start method should handle empty list of meals`() {
        // Given
        every { getAllSeafoodMealsUseCase.getAllSeafoodMeals() } returns emptyList()

        // When
        seaFoodMealsUI.start()

        // Then
        verify(exactly = 1) { getAllSeafoodMealsUseCase.getAllSeafoodMeals() }
        val output = outputStreamCaptor.toString().trim()
        Truth.assertThat(output).isEmpty()
    }

    @Test
    fun `start method should call use case and print results`() {
        // Given
        val seafoodMeals = listOf(
            SeafoodMeal(name = "tuna", 10.0),
            SeafoodMeal(name = "fish", 10.0),
            SeafoodMeal(name = "estakoza", 10.0),
        )
        every { getAllSeafoodMealsUseCase.getAllSeafoodMeals() } returns seafoodMeals


        // When
        seaFoodMealsUI.start()

        // Then

        val output = outputStreamCaptor.toString()
        val normalizedOutput = output.replace("\r\n", "\n").trim()

        val expectedOutput = """
                            SeafoodMeal(name=tuna, protein=10.0)
                            SeafoodMeal(name=fish, protein=10.0)
                            SeafoodMeal(name=estakoza, protein=10.0)
        """.trimIndent().replace("\r\n", "\n")

        Truth.assertThat(normalizedOutput).isEqualTo(expectedOutput)

    }

}