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


    val meal1 = createMeal(
        name = "a bit different  breakfast pizza",
        id = 1,
        minutes = 15,
        stepsCount = 5,
        description = "this warms well in the microwave for those late risers"
    )
    val meal2 = createMeal(
        name = "aww  marinated olives",
        id = 2,
        minutes = 33,
        stepsCount = 6,
        description = "toast the fennel seeds and lightly crush them"
    )
    val mealWithoutDescription = createMeal(
        name = "berry  good sandwich spread",
        id = 3,
        minutes = 33,
        stepsCount = 6,
        description = null
    )

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
        every { suggestNewKetoMealUseCase.execute(setOf()) } returns meal1
        every { reader.getUserInput() } returns "1"

        // When
        ketoDietMealUI.start()

        // Then
        verify {
            suggestNewKetoMealUseCase.execute(setOf())
            viewer.show("Name of keto meal : a bit different  breakfast pizza \nDescription of this keto meal : this warms well in the microwave for those late risers")
            viewer.show("Enter (1) if you like Keto meal to view it's details \n and (0) if you don't like it to suggest another Keto meal: ")
            viewer.show(meal1.toString())
        }
    }

    @Test
    fun `Should ask user for valid option When input is null`() {
        // Given
        every { suggestNewKetoMealUseCase.execute(setOf()) } returns meal1
        every { reader.getUserInput() } returnsMany listOf(null,"1")

        // When
        ketoDietMealUI.start()

        // Then
        verify {
            viewer.show("Please enter your right option.\n")
            viewer.show("Enter (1) if you like Keto meal to view it's details \n and (0) if you don't like it to suggest another Keto meal: ")
            viewer.show(meal1.toString())

        }
    }

    @Test
    fun `Should suggest new meal When user options 0`() {
        // Given
        every { suggestNewKetoMealUseCase.execute(setOf()) } returns meal1
        every { suggestNewKetoMealUseCase.execute(setOf(1)) } returns meal2
        every { reader.getUserInput() } returnsMany listOf("0","1")

        // When
        ketoDietMealUI.start()

        // Then
        verify {
            viewer.show("lets try another one")
            suggestNewKetoMealUseCase.execute(setOf(1))
            viewer.show("Name of keto meal : aww  marinated olives \nDescription of this keto meal : toast the fennel seeds and lightly crush them")
        }
    }

    @Test
    fun `Should display meal without description When description is null`() {
        // Given
        every { suggestNewKetoMealUseCase.execute(any()) } returns mealWithoutDescription
        every { reader.getUserInput() } returns "1"

        // When
        ketoDietMealUI.start()

        // Then
        verify {
            suggestNewKetoMealUseCase.execute(setOf())
            viewer.show("Name of keto meal : berry  good sandwich spread \n")
            viewer.show("Enter (1) if you like Keto meal to view it's details \n and (0) if you don't like it to suggest another Keto meal: ")
            viewer.show(mealWithoutDescription.toString())
        }
    }



    @Test
    fun `should display no meal found to Suggest when filter returns empty list`() {
        // Given
        every { suggestNewKetoMealUseCase.execute(any()) } throws NoSuchElementException()

        // When
        ketoDietMealUI.start()

        // Then
        verify { viewer.show("There is no Meal Suggest!") }
    }

}