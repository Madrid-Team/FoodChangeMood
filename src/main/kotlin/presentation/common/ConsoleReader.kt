package presentation.common

class ConsoleReader : Reader {
    override fun getUserInput(): String? {
        return readlnOrNull()
    }
}