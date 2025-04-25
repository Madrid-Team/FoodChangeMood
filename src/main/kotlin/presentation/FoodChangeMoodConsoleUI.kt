package presentation

import presentation.common.BaseUIController
import presentation.common.Reader

class FoodChangeMoodConsoleUI(
    private val controllers: List<BaseUIController>,
    private val reader: Reader
) : BaseUIController {
    override val id: Int = 0
    override val message: String = "---Welcome to food change mood app---"

    override fun start() {
        while (true) {
            presentFeatures()
            val input = reader.getUserInput()?.trim()?.toIntOrNull()
            if (input == 0) {
                println("Exiting from the program")
                break
            }
            if (input in 1..15) {
                input.let { choice ->
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

    private fun presentFeatures() {
        println(message)
        println("Choose feature between 1 to 15 .. press zero if you want to exit")
        controllers.sortedBy { it.id }.forEach { controller ->
            println(controller.message)
        }
    }

    private fun letUserTryAgain(): Boolean {
        println(
            "You have entered invalid input \n " +
                    "If you want to continue press 1 if you want to end the program press 0"
        )
        return when (reader.getUserInput()?.toInt()) {
            1 -> true
            0 -> false
            else -> {
                println("Invalid Input")
                letUserTryAgain()
            }
        }
    }
}