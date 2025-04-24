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

}