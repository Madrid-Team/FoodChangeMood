package presentation

import presentation.common.BaseUIController
import presentation.common.Reader
import presentation.common.Viewer

class FoodChangeMoodConsoleUI(
    private val controllers: List<BaseUIController>,
    private val reader: Reader,
    private val viewer: Viewer,
) : BaseUIController {
    override val id: Int = 0
    override val message: String = "---Welcome to food change mood app---"

    override fun start() {
        while (true) {
            presentFeatures()
            val input = reader.getUserInput()
            if (input == "0") {
                viewer.show("Exiting from the program")
                break
            }
            if (input?.toInt() != null){
                if (input.toInt() in 1..controllers.size) {
                    input.toInt().let { choice ->
                        controllers.find { it.id == choice }
                    }?.start()
                    Thread.sleep(3000)
                } else {
                    if (!letUserTryAgain()) {
                        break
                    }
                }
            }
        }
    }

    private fun presentFeatures() {
        viewer.show(message)
        viewer.show("Choose feature between 1 to 15 .. press zero if you want to exit")
        controllers.sortedBy { it.id }.forEach { controller ->
            viewer.show(controller.message)
        }
    }

    private fun letUserTryAgain(): Boolean {
        viewer.show("You have entered invalid input \n")
        viewer.show("If you want to continue press 1 if you want to end the program press 0")
        return when (reader.getUserInput()) {
            "1" -> true
            "0" -> false
            else -> {
                viewer.show("Invalid Input")
                letUserTryAgain()
            }
        }
    }
}