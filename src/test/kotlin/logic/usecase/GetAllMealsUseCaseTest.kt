package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.utilities.MealsExceptions
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import logic.helper.createMeal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test


class GetAllMealsUseCaseTest() {


    private lateinit var mealsRepository: MealsRepository
    private lateinit var getAllMealsUseCase: GetAllMealsUseCase

    @BeforeEach
    fun setUp() {

        mealsRepository = mockk(relaxed = true)
        getAllMealsUseCase = GetAllMealsUseCase(mealsRepository)
    }


    @Test
    fun `getAllMeals should returns all meals list`() {

        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(1, "Pizza"),
            createMeal(2, "Koshari"),
            createMeal(3, "Fish"),
        )

        //When
        val result = getAllMealsUseCase.getAllMeals()

        //Then
        assertThat(result).containsExactly(
            createMeal(1, "Pizza"),
            createMeal(2, "Koshari"),
            createMeal(3, "Fish"),
        )

    }


    @Test
    fun `getAllMeals should throw exception if meals list is empty`() {

        //Given
        every { mealsRepository.getAllMeals() } throws MealsExceptions.MealNotFoundException("")

        //When & Then
        assertThrows<MealsExceptions.MealNotFoundException> {
            getAllMealsUseCase.getAllMeals()
        }
    }

}