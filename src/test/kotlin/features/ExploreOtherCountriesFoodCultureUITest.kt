package features

import createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.ExploreOtherCountriesFoodUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.Reader
import presentation.features.ExploreOtherCountriesFoodCultureUI

class ExploreOtherCountriesFoodCultureUITest {
    private lateinit var exploreOtherCountriesFoodUseCaseUI: ExploreOtherCountriesFoodCultureUI
    private val exploreOtherCountriesFoodUseCase: ExploreOtherCountriesFoodUseCase = mockk(relaxed = true)
    private val reader: Reader = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        exploreOtherCountriesFoodUseCaseUI =
            ExploreOtherCountriesFoodCultureUI(exploreOtherCountriesFoodUseCase, reader)
    }

    @Test
    fun `ui should return countries meals when add valid country`() {
        //given
        every { exploreOtherCountriesFoodUseCase.getSearchedCountryMeals(any()) } returns listOf(meal)

        //when
        exploreOtherCountriesFoodUseCaseUI.start()

        //then
        verify { exploreOtherCountriesFoodUseCase.getSearchedCountryMeals(any()) }
    }

    @Test
    fun `ui should throw exception when search about not found country`() {
        //give
        every { exploreOtherCountriesFoodUseCase.getSearchedCountryMeals(any()) } throws Exception()

        //when
        exploreOtherCountriesFoodUseCaseUI.start()

        //then
        verify { exploreOtherCountriesFoodUseCase.getSearchedCountryMeals(any()) }
    }

    companion object {
        private val meal = createMeal(id = 1, name = "meal", description = "description")
    }
}