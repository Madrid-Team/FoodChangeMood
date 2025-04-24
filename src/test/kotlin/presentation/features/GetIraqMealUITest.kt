package presentation.features

import com.google.common.truth.Truth
 import io.mockk.every
import io.mockk.mockk
import logic.usecase.GetAllIraqiMealsUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import createMeal
import io.mockk.verify
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.Date
import javax.xml.crypto.Data
import kotlin.test.Test

class GetIraqMealUITest {

    private lateinit var getAllIraqiMealsUseCase: GetAllIraqiMealsUseCase
    private lateinit var getIraqMealUI:GetIraqMealUI
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        getAllIraqiMealsUseCase = mockk(relaxed = true)
        getIraqMealUI = GetIraqMealUI(getAllIraqiMealsUseCase)
        System.setOut(PrintStream(outputStreamCaptor))

    }


    @Test
    fun `controller properties should have correct values`() {
        // Given
        val mealUiId = 3
        val mealUiMessage = "3- Get All Iraq meals "
        // Then
        Truth.assertThat(getIraqMealUI.id).isEqualTo(mealUiId)
        Truth.assertThat(getIraqMealUI.message).isEqualTo(mealUiMessage)
    }

    @Test
    fun `start method should handle empty list of meals`() {
        // Given
        every { getAllIraqiMealsUseCase.getAllIraqiMeals() } returns emptyList()

        // When
        getIraqMealUI.start()

        // Then
        verify(exactly = 1) { getAllIraqiMealsUseCase.getAllIraqiMeals() }
        val output = outputStreamCaptor.toString().trim()
        Truth.assertThat(output).isEmpty()
    }

    @Test
    fun `start method should call use case and print results`() {
        // Given

        val iraqiMeals = listOf(
            createMeal(name = "Dolma", tags = listOf("iraqi"), submitted = Date(0)),
            createMeal(name = "Masgouf", tags = listOf("iraqi"), submitted = Date(0)),
            createMeal(name = "Quzi", tags = listOf("iraqi"), submitted = Date(0)),
        )
        every { getAllIraqiMealsUseCase.getAllIraqiMeals() } returns iraqiMeals

        // When
        getIraqMealUI.start()

        // Then

        val output = outputStreamCaptor.toString()
        val normalizedOutput = output.replace("\r\n", "\n").trim()

        val expectedOutput = """
            Meal(name=Dolma, id=0, minutes=15, tags=[iraqi], description=description, nutrition=Nutrition(calories=10.0, totalFat=10.0, sugar=10.0, sodium=10.0, saturatedFat=10.0, carbohydrates=10.0, protein=10.0), steps=Steps(steps=[STEP_1, STEP_2, STEP_3], stepsCount=3), ingredients=Ingredients(ingredients=[INGREDIENTS_1, INGREDIENTS_2, INGREDIENTS_3], ingredientsCount=3), submitted=Thu Jan 01 02:00:00 EET 1970, contributorId=0)
            Meal(name=Masgouf, id=0, minutes=15, tags=[iraqi], description=description, nutrition=Nutrition(calories=10.0, totalFat=10.0, sugar=10.0, sodium=10.0, saturatedFat=10.0, carbohydrates=10.0, protein=10.0), steps=Steps(steps=[STEP_1, STEP_2, STEP_3], stepsCount=3), ingredients=Ingredients(ingredients=[INGREDIENTS_1, INGREDIENTS_2, INGREDIENTS_3], ingredientsCount=3), submitted=Thu Jan 01 02:00:00 EET 1970, contributorId=0)
            Meal(name=Quzi, id=0, minutes=15, tags=[iraqi], description=description, nutrition=Nutrition(calories=10.0, totalFat=10.0, sugar=10.0, sodium=10.0, saturatedFat=10.0, carbohydrates=10.0, protein=10.0), steps=Steps(steps=[STEP_1, STEP_2, STEP_3], stepsCount=3), ingredients=Ingredients(ingredients=[INGREDIENTS_1, INGREDIENTS_2, INGREDIENTS_3], ingredientsCount=3), submitted=Thu Jan 01 02:00:00 EET 1970, contributorId=0)
        """.trimIndent().replace("\r\n", "\n")

        Truth.assertThat(normalizedOutput).isEqualTo(expectedOutput)
     }
}