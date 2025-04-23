package data.csvHandler

import data.utilities.*
import java.io.File
import java.io.FileNotFoundException
import java.security.cert.Extension

class MealsCsvReader(
    private val file: File
) {

    fun readCsvFile(): List<String> {
        if (file.exists()) {
            return getAllRows()
        } else
            throw FileNotFoundException(String.fileNotFound)
    }

    private fun getAllRows(): List<String> {
        val rows = mutableListOf<String>()
        var currentRow = String.empty

        for (line in file.readLines().dropHeader()) {
            currentRow = currentRow.appendLine(line)

            val quoteCount = currentRow.count { it == Char.doubleQuotes }
            if (quoteCount.isEven) {
                rows.add(currentRow)
                currentRow = String.empty
            }
        }

        return if (rows.isEmpty())
            throw MealsExceptions.MealNotFoundException("No meals found")
        else
            rows
    }


}