package presentation

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.BaseUIController
import presentation.common.Reader
import presentation.common.Viewer
import kotlin.test.assertEquals

class FoodChangeMoodConsoleUITest {

    private lateinit var reader: Reader
    private lateinit var viewer: Viewer
    private lateinit var foodChangeMoodUI: FoodChangeMoodConsoleUI
    private lateinit var controllers: List<BaseUIController>

    @BeforeEach
    fun setUp() {
        reader = mockk(relaxed = true)
        viewer = mockk(relaxed = true)

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
    fun `Should display welcome message when start`() {
        // Given
        every { reader.getUserInput() } returns "0"

        // When
        foodChangeMoodUI.start()

        // Then
        verify { viewer.show("---Welcome to food change mood app---")}
        verify { viewer.show("Choose feature between 1 to 15 .. press zero if you want to exit") }
        verify { viewer.show("Feature 1") }
        verify { viewer.show("Feature 2") }
        verify { viewer.show("Feature 3") }
    }

    @Test
    fun `Should display exit message when exit`() {
        // Given
        every { reader.getUserInput() } returns "0"

        // When
        foodChangeMoodUI.start()

        // Then
        verify { viewer.show("Exiting from the program") }
    }

    @Test
    fun `Should check for default value for id and message When start`() {
        assertEquals(0,foodChangeMoodUI.id)
        assertEquals("---Welcome to food change mood app---",foodChangeMoodUI.message)
    }

    @Test
    fun `Should choose correct number`() {
        // Given
        every { reader.getUserInput() } returnsMany listOf("3","0")

        // When
        foodChangeMoodUI.start()

        // Then
        verify { controllers[2].start() }
    }

    @Test
    fun `Should let user try again When choose incorrect number`() {
        // Given
        every { reader.getUserInput() } returnsMany listOf("4","77","asd","0")

        // When
        foodChangeMoodUI.start()

        // Then
        verify(exactly = 3) {
            viewer.show("You have entered invalid input \n")
            viewer.show("If you want to continue press 1 if you want to end the program press 0")
        }
        verify(exactly = 2)  { viewer.show("Invalid Input") }
    }

    @Test
    fun `Should break When choose zero to end the program`() {
        // Given
        every { reader.getUserInput() } returnsMany listOf("4","0")

        // When
        foodChangeMoodUI.start()

        // Then
        verify { viewer.show("You have entered invalid input \n") }
        verify { viewer.show("If you want to continue press 1 if you want to end the program press 0") }
    }

    @Test
    fun `Should continue When choose one to end the program`() {
        // Given
        every { reader.getUserInput() } returnsMany listOf("4","1","0")

        // When
        foodChangeMoodUI.start()

        // Then
        verify { viewer.show("You have entered invalid input \n") }
        verify { viewer.show("If you want to continue press 1 if you want to end the program press 0") }
        verify { viewer.show("---Welcome to food change mood app---")}
        verify { viewer.show("Choose feature between 1 to 15 .. press zero if you want to exit") }
        verify { viewer.show("Feature 1") }
        verify { viewer.show("Feature 2") }
        verify { viewer.show("Feature 3") }
    }

}