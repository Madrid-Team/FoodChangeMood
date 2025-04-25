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


}