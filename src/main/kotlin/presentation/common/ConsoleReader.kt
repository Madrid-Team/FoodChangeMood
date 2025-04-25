package presentation.common

class ConsoleReader : Reader {
    override fun getUserInput(): String? {
        return readlnOrNull()
    }

    override fun readDouble(): Double? {
        return readlnOrNull()?.toDouble()
    }
}