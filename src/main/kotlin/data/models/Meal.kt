package data.models

data class Meal (
    val name:String,
    val id:Int,
    val minutes:Int,
    val tags:List<String>,
    val description:String?,
    val nutrition:Nutrition,
    val steps:Steps,
    val ingredients:Ingredients,
    val submitted:String,
    val contributorId:Int,
){
    fun toSeafoodMeal() = SeafoodMeal(
        name = this.name,
        protein = this.nutrition.protein
    )
}