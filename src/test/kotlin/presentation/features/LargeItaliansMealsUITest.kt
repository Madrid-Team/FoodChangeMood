package presentation.features

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.GetItalianFoodForLargeGroupsUseCase
import logic.usecase.GetMealsSuitableForGymUseCase
import logic.usecase.createMeal
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.ConsoleReader
import presentation.common.ConsoleViewer

class LargeItaliansMealsUITest {
    private lateinit var getMealsSuitableForGymUseCase: GetItalianFoodForLargeGroupsUseCase
    private lateinit var viewer: ConsoleViewer
    private lateinit var largeItaliansMealsUI: LargeItaliansMealsUI

    @BeforeEach
    fun setUpe() {
        viewer = mockk(relaxed = true)
        getMealsSuitableForGymUseCase = mockk(relaxed = true)
        largeItaliansMealsUI = LargeItaliansMealsUI(getMealsSuitableForGymUseCase, viewer)
    }

    @Test
    fun `should show NoSuchElementException message when list is empty `() {
        every { getMealsSuitableForGymUseCase.getItalianFoodForLargeGroups() } returns emptyList()

        largeItaliansMealsUI.start()

        verify {
            viewer.show("no element found")
        }
    }


}

