package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import data.utilities.MealsExceptions
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class GetFoodByAddDateUseCaseTest {
    
    private lateinit var useCase: GetFoodByAddDateUseCase
    private lateinit var mealRepository: MealsRepository
    private lateinit var testMeals: List<Meal>
    
    @BeforeEach
    fun setUp() {
        mealRepository = mockk()
        useCase = GetFoodByAddDateUseCase(mealRepository)
        
        testMeals = listOf(
            createMeal(1, Date()),
            createMeal(2, Date()),
            createMeal(3, Date())
        )
    }
    
    @Test
    fun `should return meals for a given date`() {
        // Given
        val dateString = "2023-04-15"
        every { mealRepository.getMealsByDate(any()) } returns testMeals
        
        // When
        val result = useCase.invoke(dateString)
        
        // Then
        assertThat(result).isEqualTo(testMeals)
        verify { mealRepository.getMealsByDate(any()) }
    }
    
    @Test
    fun `should throw exception when no meals found for a date`() {
        // Given
        val dateString = "2023-04-15"
        every { mealRepository.getMealsByDate(any()) } returns emptyList()
        
        // When/Then
        val exception = assertThrows<MealsExceptions.MealNotFoundException> {
            useCase.invoke(dateString)
        }
        
        // Then
        assertThat(exception.message).contains(dateString)
        verify { mealRepository.getMealsByDate(any()) }
    }
    
    private fun createMeal(id: Int, date: Date): Meal {
        return Meal(
            name = "Meal$id",
            id = id,
            minutes = 30,
            tags = listOf("tag1", "tag2"),
            description = "Test meal $id",
            nutrition = Nutrition(
                calories = 100.0,
                totalFat = 10.0,
                sugar = 5.0,
                sodium = 200.0,
                saturatedFat = 2.0,
                carbohydrates = 30.0,
                protein = 15.0
            ),
            steps = Steps(
                steps = listOf("step1", "step2"),
                stepsCount = 2
            ),
            ingredients = Ingredients(
                ingredients = listOf("ingredient1", "ingredient2"),
                ingredientsCount = 2
            ),
            submitted = date,
            contributorId = 100 + id
        )
    }
}