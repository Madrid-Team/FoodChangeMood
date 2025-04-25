package presentation.features

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import logic.usecase.StartGuessGameUseCase
import org.junit.jupiter.api.BeforeEach
import presentation.common.Reader
import presentation.common.Viewer
import kotlin.test.Test

class GuessGameConsoleUiTest {
    private lateinit var startGuessGameUseCase: StartGuessGameUseCase
    private lateinit var viewer: Viewer
    private lateinit var reader: Reader
    private lateinit var ui: GuessGameConsoleUi

    @BeforeEach
    fun setUp() {
        startGuessGameUseCase = mockk(relaxed = true)
        viewer = mockk(relaxed = true)
        reader = mockk(relaxed = true)
        ui = GuessGameConsoleUi(startGuessGameUseCase, viewer, reader)
    }

    @Test
    fun `should have correct id when chose game and message`() {
        assertThat(ui.id).isEqualTo(5)
        assertThat(ui.message).contains("Guess game")
        assertThat(ui.message).contains("you have 3 attempts")
    }

    @Test
    fun `should continue for 3 attempts and show final message when all guesses are incorrect`() {
        // Given
        every { startGuessGameUseCase.startGuessGame() } returns Pair("Pizza", 20)
        every { reader.getUserInput() } returnsMany listOf("10", "15", "90")

        // When
        ui.start()

        // Then
        verifySequence {
            startGuessGameUseCase.startGuessGame()
            viewer.show("Guess the preparation time for: Pizza")
            viewer.show("You have 3 attempts.")

            viewer.show("Attempt 1: Enter your guess in minutes: ")
            viewer.show("Too low! Try a higher number.")

            viewer.show("Attempt 2: Enter your guess in minutes: ")
            viewer.show("Too low! Try a higher number.")

            viewer.show("Attempt 3: Enter your guess in minutes: ")
            viewer.show("Too high! Try a lower number.")

            viewer.show("Sorry, you've used all your attempts.")
            viewer.show("The correct preparation time for Pizza is 20 minutes.")
        }
    }

    @Test
    fun `should treat invalid input as 0 when user inputs string and continue`() {
        // Given
        every { startGuessGameUseCase.startGuessGame() } returns Pair("Pizza", 20)
        every { reader.getUserInput() } returnsMany listOf("abc", "20")

        // When
        ui.start()

        // Then
        verifySequence {
            startGuessGameUseCase.startGuessGame()
            viewer.show("Guess the preparation time for: Pizza")
            viewer.show("You have 3 attempts.")

            viewer.show("Attempt 1: Enter your guess in minutes: ")
            viewer.show("Too low! Try a higher number.")

            viewer.show("Attempt 2: Enter your guess in minutes: ")
            viewer.show("Correct! The preparation time is 20 minutes.")
        }
    }
    @Test
    fun `should finish game with success message when guess is correct from first attempt`() {
        // Given
        every { startGuessGameUseCase.startGuessGame() } returns Pair("Pizza", 20)
        every { reader.getUserInput() } returns "20"

        // When
        ui.start()

        // Then
        verifySequence {
            startGuessGameUseCase.startGuessGame()
            viewer.show("Guess the preparation time for: Pizza")
            viewer.show("You have 3 attempts.")

            viewer.show("Attempt 1: Enter your guess in minutes: ")
            viewer.show("Correct! The preparation time is 20 minutes.")
        }
    }

}