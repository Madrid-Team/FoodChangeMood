package presentation.common

class ConsoleViewer: Viewer {
    override fun show(message: String) {
        println(message)
    }
}