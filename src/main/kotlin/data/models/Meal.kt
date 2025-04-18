package data.models

import java.util.Date

data class Meal (
    val name:String,
    val id:Int,
    val minutes:Int,
    val tags:List<String>,
    val description:String?,
    val nutrition:Nutrition,
    val steps:Steps,
    val ingredients:Ingredients,
    val submitted:Date,
    val contributorId:Int,
)