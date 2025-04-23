package presentation.features

import logic.usecase.GetSweetsWithNoEggsUseCase
import presentation.common.BaseUIController

class SweetWithNoEggsUI(
    private val getSweetsWithNoEggsUseCase: GetSweetsWithNoEggsUseCase
) : BaseUIController {
    override val id: Int = 6
    override val message: String =
        "6- Get one sweet that not contains no eggs .. \n" +
                "- Write yes if you like it and want more details about this meal.\n" +
                "- Write no if you dislike it and want another sweet."

    override fun start() {
        println("Test Test .. Add your feature here")
    }
}