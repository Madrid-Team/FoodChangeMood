package presentation.features

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.GetMealsSuitableForGymUseCase
import logic.usecase.createMeal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.ConsoleReader
import presentation.common.ConsoleViewer

class GymHelperUITest {
    private lateinit var getMealsSuitableForGymUseCase: GetMealsSuitableForGymUseCase
    private lateinit var reader: ConsoleReader
    private lateinit var viewer: ConsoleViewer
    private lateinit var gymHelperUI: GymHelperUI

    @BeforeEach
    fun setUpe() {
        reader = mockk(relaxed = true)
        viewer = mockk(relaxed = true)
        getMealsSuitableForGymUseCase = mockk(relaxed = true)
        gymHelperUI = GymHelperUI(getMealsSuitableForGymUseCase, viewer, reader)
    }

    @Test
    fun `should show Invalid input when calories is null`() {
        every { reader.readDouble() } returns null

        gymHelperUI.start()

        verify {
            viewer.show("Invalid input")
        }
    }

    @Test
    fun `should show NoSuchElementException message when list is empty `() {
        val calories = 5.5
        val protein = 7.8
        every { reader.readDouble() } returns calories andThen protein
        every { getMealsSuitableForGymUseCase.getMealsWithinCalorieAndProteinRange(calories, protein) } returns listOf(
            createMeal(320.0, 25.0),
            createMeal(38.0, 92.0),
            createMeal(60.0, 30.0),
            createMeal(150.0, 210.0)
        )

        gymHelperUI.start()

        verify {
            viewer.show("No meals found matching calories and proten even withn range 10")
        }
    }




}