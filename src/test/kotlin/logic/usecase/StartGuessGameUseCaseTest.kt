package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class StartGuessGameUseCaseTest {
    private lateinit var mealsRepository: MealsRepository
    private lateinit var useCase: StartGuessGameUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk()
        useCase = StartGuessGameUseCase(mealsRepository)
    }

    @Test
    fun `should return correct meal name`() {

        every { mealsRepository.getAllMeals() } returns listOf(
            createTestMeal(id = 1, calories = 500.0, description = "").copy(
                name = "Spaghetti",
                minutes = 20
            )
        )

        val (name, _) = useCase.startGuessGame()

        assertThat(name).isEqualTo("Spaghetti")
    }

    @Test
    fun `should return correct meal preparation time`() {

        every { mealsRepository.getAllMeals() } returns listOf(
            createTestMeal(id = 1, calories = 500.0, description = "").copy(
                name = "Spaghetti",
                minutes = 20
            )
        )

        val (_, minutes) = useCase.startGuessGame()

        assertThat(minutes).isEqualTo(20)
    }

    @Test
    fun `should return correct meal name and preparation time`() {

        every { mealsRepository.getAllMeals() } returns listOf(
            createTestMeal(id = 1, calories = 500.0, description = "").copy(
                name = "Spaghetti",
                minutes = 20
            )
        )

        val result = useCase.startGuessGame()

        assertThat(result).isEqualTo("Spaghetti" to 20)
    }

    @Test
    fun `should return random meal name from list`() {
        val meals = listOf(
            createTestMeal(id = 1, calories = 500.0, description = "").copy(name = "Pizza", minutes = 15),
            createTestMeal(id = 2, calories = 500.0, description = "").copy(name = "Burger", minutes = 10),
            createTestMeal(id = 3, calories = 500.0, description = "").copy(name = "Pasta", minutes = 25)
        )
        every { mealsRepository.getAllMeals() } returns meals

        // When
        val (name, _) = useCase.startGuessGame()

        // Then
        assertThat(meals.map { it.name }).contains(name)
    }

    @Test
    fun `should return random meal preparation time from list`() {
        val meals = listOf(
            createTestMeal(id = 1, calories = 500.0, description = "").copy(name = "Pizza", minutes = 15),
            createTestMeal(id = 2, calories = 500.0, description = "").copy(name = "Burger", minutes = 10),
            createTestMeal(id = 3, calories = 500.0, description = "").copy(name = "Pasta", minutes = 25)
        )
        every { mealsRepository.getAllMeals() } returns meals

        // When
        val (_, minutes) = useCase.startGuessGame()

        // Then
        assertThat(meals.map { it.minutes }).contains(minutes)
    }


}