package data.csvHandler

import java.io.File
import java.io.FileNotFoundException

class MealsCsvReader(
    private val file: File
) {

    fun readCsvFile(): List<String> {
        if (file.exists()) {

            val rows = mutableListOf<String>()
            var currentRow = ""

            for (line in file.readLines().drop(1)) {
                currentRow = if (currentRow.isEmpty()) line else currentRow + line

                val quoteCount = currentRow.count { it == '"' }
                if (quoteCount % 2 == 0) {
                    rows.add(currentRow)
                    currentRow = ""
                }
            }

            return rows
        } else
            throw FileNotFoundException("File not found")
    }



}