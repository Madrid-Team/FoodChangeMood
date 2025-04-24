package presentation

import createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.MealSearchingByNameUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.Reader
import presentation.features.SearchMealByNameUI

class SearchMealByNameUITest {
    private lateinit var searchMealByNameUI: SearchMealByNameUI
    private val mealSearchingByNameUseCase: MealSearchingByNameUseCase = mockk(relaxed = true)
    private val reader: Reader = mockk(relaxed = true)

    @BeforeEach
    fun setup() {
        searchMealByNameUI = SearchMealByNameUI(mealSearchingByNameUseCase, reader)
    }

    @Test
    fun `ui should return meals when add valid meal name`() {
        //given
        every { mealSearchingByNameUseCase.searchAboutMealByName(any()) } returns listOf(meal)

        //when
        searchMealByNameUI.start()

        //then
        verify { mealSearchingByNameUseCase.searchAboutMealByName(any()) }
    }

    @Test
    fun `ui should throw exception when search about not found meal`() {
        //give
        every { mealSearchingByNameUseCase.searchAboutMealByName(any()) } throws Exception()

        //when
        searchMealByNameUI.start()

        //then
        verify { mealSearchingByNameUseCase.searchAboutMealByName(any()) }
    }

    companion object {
        private val meal = createMeal(id = 1, name = "meal", description = "description")
    }
}