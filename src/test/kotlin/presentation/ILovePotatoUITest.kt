package presentation

import createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.ShowRandomMealsIncludePotatoesUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.Viewer
import presentation.features.ILovePotatoUI

class ILovePotatoUITest {
    private lateinit var iLovePotatoUI: ILovePotatoUI
    private val showRandomMealsIncludePotatoesUseCase: ShowRandomMealsIncludePotatoesUseCase = mockk(relaxed = true)
    private val consoleViewer: Viewer = mockk(relaxed = true)

    @BeforeEach
    fun setup() {
        iLovePotatoUI = ILovePotatoUI(showRandomMealsIncludePotatoesUseCase, consoleViewer)
    }

    @Test
    fun `should ui should return 10 meals contains potatoes in their ingredients`() {
        //given
        every { showRandomMealsIncludePotatoesUseCase.showRandomMealsIncludePotatoes() } returns mealsWithPotatoes

        //when
        iLovePotatoUI.start()

        //then
        verify(exactly = 1) { showRandomMealsIncludePotatoesUseCase.showRandomMealsIncludePotatoes() }
    }

    @Test
    fun `ui should throw exception when list is empty`() {
        //give
        every { showRandomMealsIncludePotatoesUseCase.showRandomMealsIncludePotatoes() } throws NoSuchElementException()

        //when
        iLovePotatoUI.start()

        //then
        verify { showRandomMealsIncludePotatoesUseCase.showRandomMealsIncludePotatoes() }
        verify { consoleViewer.show(NoSuchElementException().message.toString()) }
    }


    companion object {
        private val mealsWithPotatoes = listOf(
            createMeal(
                id = 1, name = "meal", description = "description", ingredients = listOf("potatoes"),
                ingredientsCount = 1
            )
        )
    }
}