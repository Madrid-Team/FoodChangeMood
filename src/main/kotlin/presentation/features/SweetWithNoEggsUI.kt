package presentation.features

import logic.usecase.GetSweetsWithNoEggsUseCase
import presentation.common.BaseUIController
import presentation.common.Viewer

class SweetWithNoEggsUI(
    private val getSweetsWithNoEggsUseCase: GetSweetsWithNoEggsUseCase,
    private val viewer: Viewer
) : BaseUIController {
    override val id: Int = 6
    override val message: String =
        "6- Get one sweet that not contains no eggs .. \n" +
                "- Write yes if you like it and want more details about this meal.\n" +
                "- Write no if you dislike it and want another sweet."

    override fun start() {

        while (true) {
            val sweet = getSweetsWithNoEggsUseCase.getOneSweetWithNoEggs()

            viewer.show("Name of sweet with no eggs : ${sweet.name} \n and description of this sweet : ${sweet.description}")

            viewer.show("Enter yes if you like sweet to view it's details \\n and no if you don't like it to suggest another sweet with no eggs")

            when (getLikeOrDislikeInput()) {
                "yes" -> {
                    viewer.show(sweet.name)
                    break
                }

                "no" -> {
                    viewer.show("lets try another one")
                }

                else -> {
                    viewer.show("please enter invalid Input")
                }
            }

        }
    }

}

private fun getLikeOrDislikeInput(): String? {
    return readlnOrNull()?.trim()?.lowercase()
}