package logic.usecase


import com.google.common.truth.Truth
import data.models.*
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import logic.helper.createMeal
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

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
            SeafoodMeal("tuna salad",1.0),
            SeafoodMeal("fish dish",1.0),
        )
    }
}