package data.csvHandler


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.models.Meal
import java.io.File
import java.io.FileNotFoundException

class MealsJsonReader(
    private val file: File
) {
    private val gson = Gson()


    fun readJsonFile(): List<Meal> {
        if (file.exists()) {
            val jsonContent = file.readText()
            val mealListType = object : TypeToken<List<Meal>>() {}.type
            return gson.fromJson(jsonContent, mealListType)
        } else {
            throw FileNotFoundException("JSON file not found: ${file.absolutePath}")
        }
    }


}