package logic.usecase.mealIngredientsGame

import com.google.common.truth.Truth.assertThat
import data.utilities.MealsExceptions
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class MakeGuessUseCaseTest {
    private val repository: MealsRepository = mockk(relaxed = true)
    private lateinit var useCase: MakeGuessUseCase

    @BeforeEach
    fun setUp() {
        useCase = MakeGuessUseCase(repository)
    }

    @Test
    fun `should return success message when guess is correct`() {
        // given
        every { repository.getCorrectGuessedMealsNames() } returns listOf("correct")
        
        // when
        val result = useCase.invoke("correct", "correct")
        
        // then
        assertThat(result).isEqualTo("that's correct! You have guessed 1 meals correctly!")
        verify {
            repository.addCorrectGuessedMealName("correct")
        }
    }
    
    @Test
    fun `should throw exception when guess is incorrect`() {
        // when/then
        val exception = assertThrows<MealsExceptions.GuessMealGameNotPassed> {
            useCase.invoke("incorrect", "correct")
        }
        
        assertThat(exception.message).isEqualTo("ooh, you didn't guess the meal name")
        verify {
            repository.clearCorrectGuessedMealsNames()
        }
    }
    
    @Test
    fun `should show correct count of guessed meals in success message`() {
        // given
        every { repository.getCorrectGuessedMealsNames() } returns listOf("meal1", "meal2", "meal3")
        
        // when
        val result = useCase.invoke("correct", "correct")
        
        // then
        assertThat(result).isEqualTo("that's correct! You have guessed 3 meals correctly!")
    }
    
    @Test
    fun `should throw game passed exception when all 15 meals are guessed correctly`() {
        // given
        val correctMeals = List(15) { "meal$it" }
        every { repository.getCorrectGuessedMealsNames() } returns correctMeals
        
        // when/then
        val exception = assertThrows<MealsExceptions.GuessMealGamePassed> {
            useCase.invoke("correct", "correct")
        }
        
        assertThat(exception.message).isEqualTo("You have guessed all the meals correctly!")
        verify {
            repository.addCorrectGuessedMealName("correct")
            repository.clearCorrectGuessedMealsNames()
        }
    }
    
    @Test
    fun `should be case sensitive when comparing guesses`() {
        // when/then
        val exception = assertThrows<MealsExceptions.GuessMealGameNotPassed> {
            useCase.invoke("Correct", "correct")
        }
        
        assertThat(exception.message).isEqualTo("ooh, you didn't guess the meal name")
    }
}