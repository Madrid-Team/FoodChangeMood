package presentation.features

import presentation.common.BaseUIController

class SweetWithNoEggsUI : BaseUIController {
    override val id: Int = 6
    override val message: String =
        "6- Get one sweet that not contains no eggs .. \n" +
                "- Write yes if you like it and want more details about this meal.\n" +
                "- Write no if you dislike it and want another sweet."

    override fun start() {
        TODO("Not yet implemented")
    }
}