package test

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import logic.usecase.MealSearchingByNameUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MealSearchingByNameUseCaseTest {
    private lateinit var mealsRepository: MealsRepository
    private lateinit var mealSearchingByNameUseCase: MealSearchingByNameUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk(relaxed = true)
        mealSearchingByNameUseCase = MealSearchingByNameUseCase(mealsRepository)
    }

    @Test
    fun `should return meals when search about valid meals`() {
        //given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeals("pasta"),
            createMeals("rice"),
            createMeals("meat")
        )

        //when
        val result = mealSearchingByNameUseCase.searchAboutMealByName("pasta")

        //then
        assertThat(result.map { it.name }).contains("pasta")
    }
}