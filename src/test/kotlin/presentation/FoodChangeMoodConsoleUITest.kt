package presentation

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.BaseUIController
import presentation.common.Reader
import presentation.common.Viewer

class FoodChangeMoodConsoleUITest {

    private lateinit var reader: Reader
    private lateinit var viewer: Viewer
    private lateinit var foodChangeMoodUI: FoodChangeMoodConsoleUI
    private lateinit var controllers: List<BaseUIController>

    @BeforeEach
    fun setUp() {
        reader = mockk(relaxed = true)
        viewer = mockk()

        val feature1 = mockk<BaseUIController>(relaxed = true)
        every { feature1.id } returns 1
        every { feature1.message } returns "Feature 1"
        val feature2 = mockk<BaseUIController>(relaxed = true)
        every { feature2.id } returns 2
        every { feature2.message } returns "Feature 2"
        val feature3 = mockk<BaseUIController>(relaxed = true)
        every { feature3.id } returns 3
        every { feature3.message } returns "Feature 3"

        controllers = listOf(feature1, feature2, feature3)
        foodChangeMoodUI = FoodChangeMoodConsoleUI(controllers, reader, viewer)
    }

    @Test
    fun `Should display welcome message when start and exit`() {
        every { reader.getUserInput()?.toIntOrNull() } returns 0 andThen 1
        foodChangeMoodUI.start()
        verify {
            viewer.show("---Welcome to food change mood app---")
            viewer.show("Choose feature between 1 to 15 .. press zero if you want to exit")
            controllers.forEach { controller ->
                viewer.show(controller.message)
            }
            viewer.show("Exiting from the program")
        }
    }
}