package logic.usecase.mealIngredientsGame

import com.google.common.truth.Truth.assertThat
import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import org.junit.jupiter.api.BeforeEach
import java.util.Date
import kotlin.test.Test

class GetNIncorrectIngredientsUseCaseTest {
    private lateinit var useCase: GetNIncorrectIngredientsUseCase
    private lateinit var testMeals: List<Meal>

    @BeforeEach
    fun setUp() {
        useCase = GetNIncorrectIngredientsUseCase()

        // Setup test meals with different ingredients
        testMeals = listOf(
            createMeal(1, listOf("salt", "pepper", "chicken")),
            createMeal(2, listOf("rice", "beans", "tomato")),
            createMeal(3, listOf("flour", "sugar", "eggs")),
            createMeal(4, listOf("beef", "onion", "garlic")),
            createMeal(5, listOf("pasta", "cheese", "basil"))
        )
    }

    @Test
    fun `should return correct number of incorrect ingredients`() {
        //  Given
        val correctIngredients = testMeals[0].ingredients.ingredients
        //  when
        val result = useCase.invoke(testMeals, correctIngredients, 3)

        // then
        assertThat(result.size).isEqualTo(3)
        assertThat(result).doesNotContain(correctIngredients)
    }

    @Test
    fun `should not contain correct ingredient in results`() {
        //  Given
        val correctIngredients = testMeals[1].ingredients.ingredients
        // when
        val result = useCase.invoke(testMeals,correctIngredients , 4)

        // then
        assertThat(result).doesNotContain(correctIngredients)
    }

    @Test
    fun `should return distinct ingredients`() {
        //  Given
        val correctIngredients = testMeals[2].ingredients.ingredients
        // when
        val result = useCase.invoke(testMeals, correctIngredients, 5)

        // then
        val distinctResult = result.distinct()
        assertThat(distinctResult.size).isEqualTo(result.size)
    }

    @Test
    fun `should return different results on multiple calls`() {
        //  Given
        val correctIngredients = testMeals[4].ingredients.ingredients
        // when
        val result1 = useCase.invoke(testMeals, correctIngredients, 3)
        val result2 = useCase.invoke(testMeals, correctIngredients, 3)

        // then
        // This test might occasionally fail due to random shuffling,
        // but it's highly unlikely that two shuffled lists will be identical
        assertThat(result1).isNotEqualTo(result2)
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