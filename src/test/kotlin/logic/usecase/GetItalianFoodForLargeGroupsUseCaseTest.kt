package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class GetItalianFoodForLargeGroupsUseCaseTest {
    private lateinit var mealRepository: MealsRepository
    private lateinit var getItalianFoodForLargeGroupsUseCase: GetItalianFoodForLargeGroupsUseCase

    @BeforeEach
    fun setup() {
        mealRepository = mockk(relaxed = true)
        getItalianFoodForLargeGroupsUseCase = GetItalianFoodForLargeGroupsUseCase(mealRepository)

    }

    @Test
    fun `should return valid meals when the list has italian food for large groups`() {
        //Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMealItalianLargeGroups(
                "pasta", 1,
                tags = listOf("spicy", "for-large-groups"),
                description = "ITALIAN food can be made in 10 minutes"
            ),

            createMealItalianLargeGroups(
                "koshari", 2,
                tags = listOf("spicy", "for-large-groups"),
                description = "egyptian food can be made in 10 minutes"
            ),

            createMealItalianLargeGroups(
                "pistatchini", 3,
                tags = listOf("salsa", "for-small-groups"),
                description = "italian food can be made in 10 minutes"
            ),
            createMealItalianLargeGroups(
                "pizza", 4,
                tags = listOf("spicy", "FOR-LARGE-Groups"),
                description = "italian food can be made in 10 minutes"
            )

        )
        //when
        val resultMeals = getItalianFoodForLargeGroupsUseCase.getItalianFoodForLargeGroups()

        //then
        assertThat(resultMeals).hasSize(2)


    }

    @Test
    fun `should return throw exception when list is empty`() {
        //Given
        every { mealRepository.getAllMeals() } returns listOf()

        //when & then
        assertThrows<Exception> {
            getItalianFoodForLargeGroupsUseCase.getItalianFoodForLargeGroups()
        }

    }


    @Test
    fun `should throw exception when no italian meals for large groups found`() {
        //Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMealItalianLargeGroups(
                "pasta", 1,
                tags = listOf("spicy", "for-small-groups"),
                description = "ITALIAN food can be made in 10 minutes"
            ),
            createMealItalianLargeGroups(
                "koshari", 2,
                tags = listOf("spicy", "for-large-groups"),
                description = "egyptian food can be made in 10 minutes"
            ),
            createMealItalianLargeGroups(
                "pistatchini", 3,
                tags = listOf("salsa", "for-small-groups"),
                description = "italian food can be made in 10 minutes"
            ),
            createMealItalianLargeGroups(
                "pizza", 4,
                tags = listOf("spicy", "FOR-LARGE-Groups"),
                description = "spanish food can be made in 10 minutes"
            )
        )
        //when & then
        assertThrows <NoSuchElementException>{ getItalianFoodForLargeGroupsUseCase.getItalianFoodForLargeGroups()  }
    }


}