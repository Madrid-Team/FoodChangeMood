package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GetMealsSuitableForGymUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var getMealsSuitableForGymUseCase: GetMealsSuitableForGymUseCase

    @BeforeEach
    fun setUp() {
        mealsRepository = mockk(relaxed = true)
        getMealsSuitableForGymUseCase = GetMealsSuitableForGymUseCase(mealsRepository)
    }

    @ParameterizedTest
    @CsvSource(
        "100.0, 39.0, 1", // calories and protein match the values in the lis
        "50.0, 20.0, 2",   // calories and protein match the values within the range
    )
    fun `Should return correct value for every input when input is correct`(
        calories: Double,
        protein: Double,
        expectedSize: Int
    ) {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(100.0, 39.0),
            createMeal(40.0, 10.0),
            createMeal(60.0, 30.0),
            createMeal(150.0, 210.0)
        )

        // When
        val mealsResult = getMealsSuitableForGymUseCase.getMealsWithinCalorieAndProteinRange(calories, protein)

        // Then
        assertThat(mealsResult).hasSize(expectedSize)
    }

    @Test
    fun `Should throw exception when calories and protein don't match the values in the lista`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(320.0, 25.0),
            createMeal(38.0, 92.0),
            createMeal(60.0, 30.0),
            createMeal(150.0, 210.0)
        )

        // When && Then
        assertThrows<NoSuchElementException> {
            getMealsSuitableForGymUseCase.getMealsWithinCalorieAndProteinRange(75.0, 5.0)
        }
    }

}