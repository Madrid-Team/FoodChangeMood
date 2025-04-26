package presentation.features

import com.google.common.truth.Truth.assertThat
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
    fun `ui class should have the correct properties value`() {
        val classId = 9
        val uiMessage = "$classId- Enter a desired amount of calories and protein,\n" +
                "and return a list of meals that match or approximate those values."

        assertThat(gymHelperUI.id).isEqualTo(classId)
        assertThat(gymHelperUI.message).isEqualTo(uiMessage)
    }

    @Test
    fun `ui should return meals when input is valid`() {
        //given
        every { reader.readDouble() } returns validCalories andThen validProtein
        every {
            getMealsSuitableForGymUseCase.getMealsWithinCalorieAndProteinRange(
                validCalories,
                validProtein
            )
        } returns meals

        //when
        gymHelperUI.start()

        //then
        meals.forEach {
            viewer.show(it.name)
        }
    }

    @Test
    fun `ui should show NoSuchElementException message when calories value not valid`() {
        every { reader.readDouble() } returns notValidCalories andThen validProtein
        every {
            getMealsSuitableForGymUseCase.getMealsWithinCalorieAndProteinRange(
                notValidCalories,
                validProtein
            )
        } throws NoSuchElementException()

        gymHelperUI.start()

        verify { viewer.show(NoSuchElementException().message.toString()) }
    }

    @Test
    fun `ui should show NoSuchElementException message when protein value not valid`() {
        every { reader.readDouble() } returns validCalories andThen notValidProtein
        every {
            getMealsSuitableForGymUseCase.getMealsWithinCalorieAndProteinRange(
                validCalories,
                notValidProtein
            )
        } throws NoSuchElementException()

        gymHelperUI.start()

        verify { viewer.show(NoSuchElementException().message.toString()) }
    }

    @Test
    fun `ui should show message when protein or protein value equal null`() {
        every { reader.readDouble() } returns null andThen null

        gymHelperUI.start()

        verify { viewer.show("Invalid input") }
    }


    companion object {
        private const val validCalories = 5.5
        private const val validProtein = 7.8

        private const val notValidCalories = 512.5
        private const val notValidProtein = 524.5

        private val meals = listOf(
            createMeal(320.0, 25.0),
            createMeal(38.0, 92.0),
            createMeal(60.0, 30.0),
            createMeal(150.0, 210.0)
        )
    }
}