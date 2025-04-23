package data.csvHandler



import com.google.gson.Gson
import com.google.gson.GsonBuilder
import data.models.Meal
import java.io.File
import java.io.FileWriter

class MealsJsonConverter {
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()


    fun saveToJsonFile(meals: List<Meal>, outputFile: File): Boolean {
        return try {

            FileWriter(outputFile).use { writer ->
                gson.toJson(meals, writer)
            }
            true
        } catch (e: Exception) {
            println("Error saving JSON file: ${e.message}")
            false
        }
    }


}