package logic.usecase

import com.google.common.truth.Truth.assertThat
import createMealsForIgnoreCaseTest
import createMealsForSubstringTest
import createMealsForSweetWithoutEggs
import createMealsFreeOfSweetWithoutEggs
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetSweetsWithNoEggsUseCaseTest {
    private lateinit var mealsRepository: MealsRepository
    private lateinit var getSweetsWithNoEggsUseCase: GetSweetsWithNoEggsUseCase

    @BeforeEach
    fun setUp() {
        mealsRepository = mockk(relaxed = true)
        getSweetsWithNoEggsUseCase = GetSweetsWithNoEggsUseCase(mealsRepository)
    }

    @Test
    fun `Should return sweet with no eggs when list have sweets don't contain egg`() {
        every { mealsRepository.getAllMeals() } returns createMealsForSweetWithoutEggs()

        val resultMeal = getSweetsWithNoEggsUseCase.getOneSweetWithNoEggs()

        assertThat(resultMeal.name).isEqualTo("Mexican Baked Squash")
    }

    @Test
    fun `should return sweet with no eggs when sweet and egg appear as substrings`() {
        every { mealsRepository.getAllMeals() } returns createMealsForSubstringTest()

        val resultMeal = getSweetsWithNoEggsUseCase.getOneSweetWithNoEggs()

        assertThat(resultMeal.name).isEqualTo("Mango Pudding")
    }

    @Test
    fun `should return sweet with no eggs when ignoring case of sweet and egg`() {
        every { mealsRepository.getAllMeals() } returns createMealsForIgnoreCaseTest()

        val resultMeal = getSweetsWithNoEggsUseCase.getOneSweetWithNoEggs()

        assertThat(resultMeal.name).isEqualTo("Fruit Salad")
    }

    @Test
    fun `Should throw NoSuchElementException when list doesn't have sweet without eggs `() {
        every { mealsRepository.getAllMeals() } returns createMealsFreeOfSweetWithoutEggs()

        assertThrows<NoSuchElementException> {
            getSweetsWithNoEggsUseCase.getOneSweetWithNoEggs()
        }
    }

}