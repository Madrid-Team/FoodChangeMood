package presentation.features

import com.google.common.truth.Truth.assertThat
import createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.usecase.GetSweetsWithNoEggsUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import presentation.common.Reader
import presentation.common.Viewer

class SweetWithNoEggsUITest {
    private lateinit var sweetWithNoEggsUI: SweetWithNoEggsUI
    private val getSweetsWithNoEggsUseCase: GetSweetsWithNoEggsUseCase = mockk(relaxed = true)
    private val viewer: Viewer = mockk(relaxed = true)
    private val reader: Reader = mockk(relaxed = true)

    @BeforeEach
    fun setup() {
        sweetWithNoEggsUI = SweetWithNoEggsUI(getSweetsWithNoEggsUseCase, viewer, reader)
    }

    @Test
    fun `ui class should have the correct properties value`() {
        val classId = 6
        val uiMessage = "$classId Get one sweet that not contains no eggs .. \n" +
                "- Write yes if you like it and want more details about this meal.\n" +
                "- Write no if you dislike it and want another sweet."

        assertThat(sweetWithNoEggsUI.id).isEqualTo(classId)
        assertThat(sweetWithNoEggsUI.message).isEqualTo(uiMessage)
    }

    @Test
    fun `ui should return sweet with details when user likes it`() {
        every { getSweetsWithNoEggsUseCase.getOneSweetWithNoEggs() } returns sweetWithNoEggs
        every { reader.getUserInput() } returns "yes"

        sweetWithNoEggsUI.start()

        verify { viewer.show("Name of sweet with no eggs : ${sweetWithNoEggs.name} \n and description of this sweet : ${sweetWithNoEggs.description}") }
        verify { viewer.show("Enter yes if you like sweet to view it's details \\n and no if you don't like it to suggest another sweet with no eggs") }
        verify { viewer.show(sweetWithNoEggs.name) }

    }

    @Test
    fun `ui should give the user second trial when dislike the sweet`() {
        every { getSweetsWithNoEggsUseCase.getOneSweetWithNoEggs() } returns sweetWithNoEggs andThen anotherSweetWithNoEggs
        every { reader.getUserInput() } returns "no" andThen "yes"

        sweetWithNoEggsUI.start()

        verify { viewer.show("Name of sweet with no eggs : ${sweetWithNoEggs.name} \n and description of this sweet : ${sweetWithNoEggs.description}") }
        verify { viewer.show("Enter yes if you like sweet to view it's details \\n and no if you don't like it to suggest another sweet with no eggs") }
        verify { viewer.show("lets try another one") }
        verify { viewer.show("Name of sweet with no eggs : ${anotherSweetWithNoEggs.name} \n and description of this sweet : ${anotherSweetWithNoEggs.description}") }
    }

    @Test
    fun `ui should show message to the user when input not yes or no`() {
        every { getSweetsWithNoEggsUseCase.getOneSweetWithNoEggs() } returns sweetWithNoEggs
        every { reader.getUserInput() } returns "bla" andThen "yes"

        sweetWithNoEggsUI.start()

        verify { viewer.show("Name of sweet with no eggs : ${sweetWithNoEggs.name} \n and description of this sweet : ${sweetWithNoEggs.description}") }
        verify { viewer.show("Enter yes if you like sweet to view it's details \\n and no if you don't like it to suggest another sweet with no eggs") }
        verify { viewer.show("please enter invalid Input") }
    }

    companion object {
        val sweetWithNoEggs = createMeal(
            name = "name", tags = listOf("tag1", "tag2")
        )

        val anotherSweetWithNoEggs = createMeal(
            name = "name1", tags = listOf("tag3", "tag4")
        )

    }
}