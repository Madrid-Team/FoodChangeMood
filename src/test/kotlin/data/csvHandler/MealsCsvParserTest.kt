package data.csvHandler

import com.google.common.truth.Truth.assertThat
import data.models.Meal
import data.utilities.parseDateString
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MealsCsvParserTest {

    private lateinit var meal: Meal
    private lateinit var mealsCsvParser: MealsCsvParser


    @BeforeEach
    fun setUp() {
        mealsCsvParser = MealsCsvParser()
        val row = "zydeco salad,367912,5,79877,2009-04-25,\"['15-minutes-or-less', 'time-to-make', 'preparation', '5-ingredients-or-less', 'easy', '3-steps-or-less']\",\"[14.1, 0.0, 8.0, 0.0, 1.0, 0.0, 1.0]\",4,\"['place the lettuce on a platter or serving dish top with tomatoes', 'then theolive salad' , 'then the three-bean salad', 'serve immediately']\",\"recipe courtesy of b&c seafood, vacherie, la adapted from alton brown's cookbook \"\"feasting on asphalt\"\".  take this to your next covered-dish dinner or picnic.  finding the italianolive salad-the chopped olive spread that's an essential component of amuffaletta sandwich-is the trick.  if you can't find it at the megamart do a search on 'zaar, there are many authentic recipes.  if you have minor's kalamata olive base, be sure to use it in the olive spread.  three bean salad: b&c seafood uses \"\"read\"\" brand of three-bean salad.  if you can't find it at your grocery store try www.senecafoods.com.  the olive salad and canned beans marry into the lettuce and tomatoes.  zydeco comes from \"\"les haricorts cont pas sales\"\", which means \"\"the beans aren't salty\"\", an expression for hard times when there wasn't enough salt meat to season the beans.  so this salad, with it's three different beans, is actually zydeco and the musical genre is named after it.  food is facinating stuff.  edited 5-18-09  i just made another batch of this, found a huge jar of the bean salad at costco.  this is so good, i could live on this salad.  you really need to try this one.  i made my olive salad from scratch this time with both kalamata and green olives, and will continue to go this route.\",\"['iceberg lettuce', 'tomatoes', '3 bean mix', 'olive salad']\",4"
        meal = mealsCsvParser.parseOnLine(row)
    }

    @Test
    fun `parseOnLine should correctly parse a valid csv row and return not null meal`(){
        assertThat(meal).isNotNull()
    }

    @Test
    fun `parseOnLine should correctly parse meal name correctly`(){
        assertThat(meal.name).contains("zydeco salad")
    }

    @Test
    fun `parseOnLine should correctly parse meal id correctly`(){
        assertThat(meal.id).isEqualTo(367912)
    }

    @Test
    fun `parseOnLine should correctly parse meal minutes correctly`(){
        assertThat(meal.minutes).isEqualTo(5)
    }


    @Test
    fun `parseOnLine should correctly parse meal contributorId correctly`(){
        assertThat(meal.contributorId).isEqualTo(79877)
    }

    @Test
    fun `parseOnLine should correctly parse meal submitted date correctly`(){
        assertThat(meal.submitted).isEqualTo(parseDateString("2009-04-25"))
    }

    @Test
    fun `parseOnLine should correctly parse meal tags correctly`(){
        assertThat(meal.tags).containsExactly(
            "15-minutes-or-less",
            "time-to-make",
            "preparation",
            "5-ingredients-or-less",
            "easy", "3-steps-or-less"
        )
    }

    @Test
    fun `parseOnLine should correctly parse meal calories correctly`(){
        assertThat(meal.nutrition.calories).isEqualTo(14.1)
    }
    @Test
    fun `parseOnLine should correctly parse meal totalFat correctly`(){
        assertThat(meal.nutrition.totalFat).isEqualTo(0.0)
    }

    @Test
    fun `parseOnLine should correctly parse meal sugar correctly`(){
        assertThat(meal.nutrition.sugar).isEqualTo(8.0)
    }


    @Test
    fun `parseOnLine should correctly parse meal sodium correctly`(){
        assertThat(meal.nutrition.sodium).isEqualTo(0.0)
    }


    @Test
    fun `parseOnLine should correctly parse meal saturatedFat correctly`(){
        assertThat(meal.nutrition.saturatedFat).isEqualTo(1.0)
    }

    @Test
    fun `parseOnLine should correctly parse meal carbohydrates correctly`(){
        assertThat(meal.nutrition.carbohydrates).isEqualTo(0.0)
    }


    @Test
    fun `parseOnLine should correctly parse meal protein correctly`(){
        assertThat(meal.nutrition.protein).isEqualTo(1.0)
    }


    @Test
    fun `parseOnLine should correctly parse meal steps number correctly`(){
        assertThat(meal.steps.stepsCount).isEqualTo(4)
    }

    @Test
    fun `parseOnLine should correctly parse meal steps correctly`(){
        assertThat(meal.steps.steps).hasSize(4)
    }

    @Test
    fun `parseOnLine should correctly parse meal description correctly`(){
        assertThat(meal.description).isNotEmpty()
    }



    @Test
    fun `parseOnLine should correctly parse meal ingredients count correctly`(){
        assertThat(meal.ingredients.ingredientsCount).isEqualTo(4)
    }


    @Test
    fun `parseOnLine should correctly parse meal ingredients list correctly`(){
        assertThat(meal.ingredients.ingredients).containsExactly(
            "iceberg lettuce", "tomatoes", "3 bean mix", "olive salad"
        )
    }

}