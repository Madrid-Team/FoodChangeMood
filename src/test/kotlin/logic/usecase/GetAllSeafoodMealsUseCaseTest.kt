package logic.usecase


import com.google.common.truth.Truth
import createMeal
import data.models.SeafoodMeal
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetAllSeafoodMealsUseCaseTest() {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var getAllSeafoodMealsUseCase: GetAllSeafoodMealsUseCase

    @BeforeEach
    fun setUp() {
        mealsRepository = mockk(relaxed = true)
        getAllSeafoodMealsUseCase = GetAllSeafoodMealsUseCase(mealsRepository)
    }

    @Test
    fun `get all seafood meals should return not empty list when has seafood meals`(){
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(name = "tuna salad",tags =  listOf("seafood","occasion")),
            createMeal(name = "fish dish",tags =  listOf("sweets","seafood")),
            createMeal(name = "rice",tags =  listOf("time-to-make","occasion")),
            createMeal(name = "pizza",tags =  listOf("time-to-make","sweets")),
        )

        // When
        val result  = getAllSeafoodMealsUseCase.getAllSeafoodMeals()

        // Then
        Truth.assertThat(result).containsExactly(
            SeafoodMeal("tuna salad",10.0),
            SeafoodMeal("fish dish",10.0),
        )
    }
    @Test
    fun `get all seafood meals should return empty list when has no seafood meals`(){
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(name = "tuna salad",tags =  listOf("occasion")),
            createMeal(name = "fish dish",tags =  listOf("sweets")),
            createMeal(name = "rice",tags =  listOf("time-to-make","occasion")),
            createMeal(name = "pizza",tags =  listOf("time-to-make","sweets")),
        )

        // When
        val result  = getAllSeafoodMealsUseCase.getAllSeafoodMeals()

        // Then
        Truth.assertThat(result).isEmpty()
    }
}