package presentation.features

import createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.SuggestMealWithHighCalorieUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.Reader
import presentation.common.Viewer

class SuggestMealWithHighCalorieUITest {
    private lateinit var suggestMealWithHighCalorieUI: SuggestMealWithHighCalorieUI
    private val suggestMealWithHighCalorieUseCase: SuggestMealWithHighCalorieUseCase = mockk(relaxed = true)
    private val viewer: Viewer = mockk(relaxed = true)
    private val reader: Reader = mockk(relaxed = true)

    @BeforeEach
    fun setup() {
        suggestMealWithHighCalorieUI = SuggestMealWithHighCalorieUI(suggestMealWithHighCalorieUseCase, viewer, reader)
    }

    @Test
    fun `ui should show message when suggested meal equal null`() {
        //given
        every { suggestMealWithHighCalorieUseCase.suggestRandomHighCalorieMeal() } returns null

        //when
        suggestMealWithHighCalorieUI.start()

        //then
        verify { suggestMealWithHighCalorieUseCase.suggestRandomHighCalorieMeal() }
        verify { viewer.show("No more high calorie meals to suggest") }
    }

    @Test
    fun `should return meal and details if user likes it`() {
        //given
        every { suggestMealWithHighCalorieUseCase.suggestRandomHighCalorieMeal() } returns firstMeal
        every { reader.getUserInput() } returns "like"

        //when
        suggestMealWithHighCalorieUI.start()

        //then
        verify { viewer.show("Suggested Meal: ${firstMeal.name} (${firstMeal.nutrition.calories} calories)") }
        verify { viewer.show("Here are the full details of the meal:") }
        verify { viewer.show("Name: ${firstMeal.name}") }
        verify { viewer.show("Description: ${firstMeal.description ?: "No description available"}") }
        verify { viewer.show("Calories: ${firstMeal.nutrition.calories}") }
        verify { viewer.show("Preparation Time: ${firstMeal.minutes} minutes") }
    }

    @Test
    fun `should return another meal if user dislike it`() {
        //given
        every { suggestMealWithHighCalorieUseCase.suggestRandomHighCalorieMeal() } returnsMany listOf(
            firstMeal,
            secondMeal
        )
        every { reader.getUserInput() } returnsMany listOf("dislike", "like")

        //when
        suggestMealWithHighCalorieUI.start()

        //then
        verify { viewer.show("Suggested Meal: ${firstMeal.name} (${firstMeal.nutrition.calories} calories)") }
        verify { viewer.show("Okay, let me suggest another meal.\n") }
        verify { viewer.show("Suggested Meal: ${secondMeal.name} (${secondMeal.nutrition.calories} calories)") }
    }

    @Test
    fun `ui show message when user's input was not valid`() {
        //given
        every { suggestMealWithHighCalorieUseCase.suggestRandomHighCalorieMeal() } returns firstMeal
        every { reader.getUserInput() } returnsMany listOf("maybe", "haha", "like")

        //when
        suggestMealWithHighCalorieUI.start()

        //then
        verify { viewer.show("Invalid input. Please type 'like', or 'dislike'") }
    }

    companion object {
        private val firstMeal = createMeal(id = 1, name = "name", description = "description")
        private val secondMeal = createMeal(id = 2, name = "name2", description = "description2")

    }
}