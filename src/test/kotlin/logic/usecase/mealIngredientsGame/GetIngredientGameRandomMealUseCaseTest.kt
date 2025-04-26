package logic.usecase.mealIngredientsGame

import com.google.common.truth.Truth.assertThat
import data.models.IngredientGameData
import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import java.util.Date
import kotlin.test.Test

class GetIngredientGameRandomMealUseCaseTest {
    private lateinit var useCase: GetIngredientGameRandomMealUseCase
    private lateinit var getNIncorrectIngredientsUseCase: GetNIncorrectIngredientsUseCase
    private lateinit var mealsRepository: MealsRepository
    private lateinit var testMeals: List<Meal>

    @BeforeEach
    fun setUp() {
        getNIncorrectIngredientsUseCase = mockk()
        mealsRepository = mockk()
        useCase = GetIngredientGameRandomMealUseCase(
            getNIncorrectIngredientsUseCase,
            mealsRepository
        )


        testMeals = listOf(
            createMeal(1, listOf("salt", "pepper", "chicken")),
            createMeal(2, listOf("rice", "beans", "tomato")),
            createMeal(3, listOf("flour", "sugar", "eggs")),
            createMeal(4, listOf("beef", "onion", "garlic")),
            createMeal(5, listOf("pasta", "cheese", "basil"))
        )

        every { mealsRepository.getAllMeals() } returns testMeals
    }

    @Test
    fun `should return valid game data with correct number of options`() {
        // Given
        val sampleIncorrectIngredients = listOf("rice", "beans")
        every { getNIncorrectIngredientsUseCase.invoke(any(), any(), 2) } returns sampleIncorrectIngredients

        // When
        val result = useCase.invoke()

        // Then
        assertThat(result).isInstanceOf(IngredientGameData::class.java)
        assertThat(result.options.size).isEqualTo(3) // 2 incorrect + 1 correct
        assertThat(result.options).contains(result.correctAnswer)
        verify { mealsRepository.getAllMeals() }
        verify { getNIncorrectIngredientsUseCase.invoke(any(), any(), 2) }
    }

    @Test
    fun `should select a meal with non-empty ingredients`() {
        // Given
        val emptyIngredientsMeal = createMeal(6, listOf())
        val testMealsWithEmpty = testMeals + emptyIngredientsMeal
        every { mealsRepository.getAllMeals() } returns testMealsWithEmpty
        every { getNIncorrectIngredientsUseCase.invoke(any(), any(), 2) } returns listOf("rice", "beans")

        // When
        val result = useCase.invoke()

        // Then
        assertThat(result.meal.ingredients.ingredients).isNotEmpty()
    }

    @Test
    fun `should include correct ingredient in options`() {
        // Given
        val sampleIncorrectIngredients = listOf("rice", "beans")
        every { getNIncorrectIngredientsUseCase.invoke(any(), any(), 2) } returns sampleIncorrectIngredients

        // When
        val result = useCase.invoke()

        // Then
        assertThat(result.options).contains(result.correctAnswer)
        assertThat(result.meal.ingredients.ingredients).contains(result.correctAnswer)
    }

    @Test
    fun `should have shuffled options`() {
        // Given
        val fixedMeal = testMeals[0] // salt, pepper, chicken
        val fixedIncorrectIngredients = listOf("rice", "beans")
        
        // Make repository return just one meal to make test deterministic
        every { mealsRepository.getAllMeals() } returns listOf(fixedMeal)
        every { getNIncorrectIngredientsUseCase.invoke(any(), any(), 2) } returns fixedIncorrectIngredients

        // When - call useCase multiple times
        val results = List(5) { useCase.invoke() }
        
        // Then - check if at least some of the option orders are different
        // This test might occasionally fail due to random shuffling,
        // but it's highly unlikely that all 5 calls will produce the same order
        val allOptionLists = results.map { it.options }
        val distinctOptionLists = allOptionLists.distinct()
        
        // If we have more than one distinct list, it means shuffling worked
        assertThat(distinctOptionLists.size).isGreaterThan(1)
    }

    private fun createMeal(id: Int, ingredients: List<String>): Meal {
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
                ingredients = ingredients,
                ingredientsCount = ingredients.size
            ),
            submitted = Date(),
            contributorId = 100 + id
        )
    }
}