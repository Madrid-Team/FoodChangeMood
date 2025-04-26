package presentation.common

interface Reader {
    fun getUserInput(): String?
    fun readDouble(): Double?
}