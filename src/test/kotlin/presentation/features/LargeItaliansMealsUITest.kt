package presentation.features

import createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.GetItalianFoodForLargeGroupsUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
    fun `should show NoSuchElementException message when list is empty`() {
        every { getMealsSuitableForGymUseCase.getItalianFoodForLargeGroups() } returns emptyList()

        largeItaliansMealsUI.start()

        verify {
            viewer.show("No italian food found for large groups")
        }
    }

    @Test
    fun `should show each meal name when list is not empty`() {
        every { getMealsSuitableForGymUseCase.getItalianFoodForLargeGroups() } returns listOf(
            createMeal(
                name = "pasta",
                tags = listOf("spicy", "for-large-groups"),
                description = "ITALIAN food can be made in 10 minutes"
            ),
            createMeal(
                name = "koshari",
                tags = listOf("spicy", "for-large-groups"),
                description = "egyptian food can be made in 10 minutes"
            ),
            createMeal(
                name = "pistatchini",
                tags = listOf("salsa", "for-small-groups"),
                description = "italian food can be made in 10 minutes"
            )
        )

        largeItaliansMealsUI.start()

        verify {
            viewer.show("pasta")
            viewer.show("pistatchini")
        }
    }

}

