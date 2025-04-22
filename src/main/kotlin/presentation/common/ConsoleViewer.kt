package presentation.common

class ConsoleViewer : Viewer {
    override fun display(message: String) {
        println(message)
    }
}