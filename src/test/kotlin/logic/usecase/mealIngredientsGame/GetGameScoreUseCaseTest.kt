package logic.usecase.mealIngredientsGame

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class GetGameScoreUseCaseTest {
    private val repository: MealsRepository = mockk(relaxed = true)
    private lateinit var useCase: GetGameScoreUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetGameScoreUseCase(repository)
    }

    @Test
    fun `should return zero when no meals are guessed correctly`() {
        // given
        every { repository.getCorrectGuessedMealsNames() } returns emptyList()
        
        // when
        val score = useCase.invoke()
        
        // then
        assertThat(score).isEqualTo(0)
    }
    
    @Test
    fun `should return 1000 when one meal is guessed correctly`() {
        // given
        every { repository.getCorrectGuessedMealsNames() } returns listOf("meal1")
        
        // when
        val score = useCase.invoke()
        
        // then
        assertThat(score).isEqualTo(1000)
    }
    
    @Test
    fun `should return correct score for multiple guessed meals`() {
        // given
        every { repository.getCorrectGuessedMealsNames() } returns listOf("meal1", "meal2", "meal3")
        
        // when
        val score = useCase.invoke()
        
        // then
        assertThat(score).isEqualTo(3000)
    }
    
    @Test
    fun `should return correct score for maximum guessed meals`() {
        // given
        val correctMeals = List(15) { "meal$it" }
        every { repository.getCorrectGuessedMealsNames() } returns correctMeals
        
        // when
        val score = useCase.invoke()
        
        // then
        assertThat(score).isEqualTo(15000)
    }
}