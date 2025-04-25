package logic.usecase

import createMeals
import data.models.Ingredients
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ShowRandomMealsIncludePotatoesUseCaseTest {
    private lateinit var mealsRepository: MealsRepository
    private lateinit var showRandomMealsIncludePotatoesUseCase: ShowRandomMealsIncludePotatoesUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk()
        showRandomMealsIncludePotatoesUseCase = ShowRandomMealsIncludePotatoesUseCase(mealsRepository)
    }

    @Test
    fun `should return list of random meals contains potatoes that size is equal to ten`() {
        //given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeals(ingredientsContainsPotatoes),
            createMeals(ingredientsContainsPotatoes),
            createMeals(ingredientsContainsPotatoes),
            createMeals(ingredientsContainsPotatoes),
            createMeals(ingredientsContainsPotatoes),
            createMeals(ingredientsContainsPotatoes),
            createMeals(ingredientsContainsPotatoes),
            createMeals(ingredientsContainsPotatoes),
            createMeals(ingredientsContainsPotatoes),
            createMeals(ingredientsContainsPotatoes),
        )

        //when
        val result = showRandomMealsIncludePotatoesUseCase.showRandomMealsIncludePotatoes()

        //them
        assertEquals(result.size, 10)
    }

    @Test
    fun `should return throw exception when list is empty`() {
        //given
        every { mealsRepository.getAllMeals() } returns listOf()

        //when && then
        assertThrows<NoSuchElementException> {
            showRandomMealsIncludePotatoesUseCase.showRandomMealsIncludePotatoes()
        }
    }

    companion object {
        private val ingredientsContainsPotatoes = Ingredients(
            ingredients = listOf("olive oil", "potatoes", " minced garlic cloves"),
            ingredientsCount = 3
        )
    }
}