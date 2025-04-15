package data.csvHandler

import java.io.File
import java.io.FileNotFoundException

class MealsCsvReader(
    private val file: File
) {

    fun readCsvFile(): List<String> {
        if (file.exists()) {
            return file.readLines()
        } else
            throw FileNotFoundException("File not found")
    }
}