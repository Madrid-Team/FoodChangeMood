package data.csvHandler

import com.google.common.truth.Truth.assertThat
import data.utilities.MealsExceptions
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Path
import kotlin.test.Test

class MealsCsvReaderTest {


    private lateinit var fakeFile: File
    private lateinit var mealsCsvReader : MealsCsvReader

    @TempDir
    lateinit var tempDir: Path

    @BeforeEach
    fun setUp() {
        fakeFile = createTestFile(tempDir)
    }

    @Test
    fun `readCsvFile should throw exception if file not found`() {
        //Given
        val file:File = mockk(relaxed = true)
        every { file.exists() } returns false

        mealsCsvReader = MealsCsvReader(file)
        //When & Then
        assertThrows<FileNotFoundException> {
            mealsCsvReader.readCsvFile()
        }
    }


    @Test
    fun `readCsvFile should not throw any exception if file exists`() {
        //Given
        fakeFile.writeText("header1,header2 \nfirst row,first row \nsecond row,second row")
        mealsCsvReader = MealsCsvReader(fakeFile)

        //When & Then
        assertDoesNotThrow{
            mealsCsvReader.readCsvFile()
        }
    }


    @Test
    fun `readCsvFile should throw exception if rows list is empty`() {
        //Given
        fakeFile.writeText("")
        mealsCsvReader = MealsCsvReader(fakeFile)

        //When && Then
        assertThrows<MealsExceptions.MealNotFoundException> {
            mealsCsvReader.readCsvFile()
        }
    }

    @Test
    fun `readCsvFile should returns list of rows`() {
        //Given
        fakeFile.writeText("header1,header2 \nfirst row,first row \nsecond row")

        //When
        mealsCsvReader = MealsCsvReader(fakeFile)
        val result = mealsCsvReader.readCsvFile()

        //Then
        assertThat(result).isNotEmpty()
    }


    @Test
    fun `readCsvFile should returns list of rows without header`() {
        //Given
        fakeFile.writeText("header1,header2 \nfirst row,first row \nsecond row,second row")

        //When
        mealsCsvReader = MealsCsvReader(fakeFile)
        val result = mealsCsvReader.readCsvFile()

        //Then
        assertThat(result).hasSize(2)
    }



    @Test
    fun `readCsvFile should returns correct list of rows if description is more than one line`() {
        //Given
        fakeFile.writeText("name,id,minutes,contributor_id,submitted,tags,nutrition,n_steps,steps,description,ingredients,n_ingredients\nzydeco salad,367912,5,79877,2009-04-25,\"['15-minutes-or-less', 'time-to-make', 'preparation', '5-ingredients-or-less', 'easy', '3-steps-or-less']\",\"[14.1, 0.0, 8.0, 0.0, 1.0, 0.0, 1.0]\",4,\"['place the lettuce on a platter or serving dish top with tomatoes', 'then theolive salad' , 'then the three-bean salad', 'serve immediately']\",\"recipe courtesy of b&c seafood, vacherie, la adapted from alton brown's cookbook \"\"feasting on asphalt\"\".  take this to your next covered-dish dinner or picnic.  finding the italianolive salad-the chopped olive spread that's an essential component of amuffaletta sandwich-is the trick.  if you can't find it at the megamart do a search on 'zaar, there are many authentic recipes.  if you have minor's kalamata olive base, be sure to use it in the olive spread.  three bean salad: b&c seafood uses \"\"read\"\" brand of three-bean salad.  if you can't find it at your grocery store try www.senecafoods.com.  the olive salad and canned beans marry into the lettuce and tomatoes.  zydeco comes from \"\"les haricorts cont pas sales\"\", which means \"\"the beans aren't salty\"\", an expression for hard times when there wasn't enough salt meat to season the beans.  so this salad, with it's three different beans, is actually zydeco and the musical genre is named after it.  food is facinating stuff.  edited 5-18-09  i just made another batch of this, found a huge jar of the bean salad at costco.  this is so good, i could live on this salad.  you really need to try this one.  i made my olive salad from scratch this time with both kalamata and green olives, and will continue to go this route.\",\"['iceberg lettuce', 'tomatoes', '3 bean mix', 'olive salad']\",4")
        fakeFile.appendText("zydeco salad,367912,5,79877,2009-04-25,\"['15-minutes-or-less', 'time-to-make', 'preparation', '5-ingredients-or-less', 'easy', '3-steps-or-less']\",\"[14.1, 0.0, 8.0, 0.0, 1.0, 0.0, 1.0]\",4,\"['place the lettuce on a platter or serving dish', 'top with tomatoes , then the', 'olive salad , then the three-bean salad', 'serve immediately']\",\"recipe courtesy of b&c seafood, vacherie, la adapted from alton brown's cookbook \"\"feasting on asphalt\"\".  take this to your next covered-dish dinner or picnic.  finding the italian\n" +
                "olive salad-the chopped olive spread that's an essential component of a\n" +
                "muffaletta sandwich-is the trick.  if you can't find it at the megamart do a search on 'zaar, there are many authentic recipes.\n" +
                "  if you have minor's kalamata olive base, be sure to use it in the olive spread.  three bean salad: b&c seafood uses \"\"read\"\" brand of three-bean salad.  if you can't find it at your grocery store try www.senecafoods.com.  the olive salad and canned beans marry into the lettuce and tomatoes.  zydeco comes from \"\"les haricorts cont pas sales\"\", which means \"\"the beans aren't salty\"\", an expression for hard times when there wasn't enough salt meat to season the beans.  so this salad, with it's three different beans, is actually zydeco and the musical genre is named after it.  food is facinating stuff.  edited 5-18-09  i just made another batch of this, found a huge jar of the bean salad at costco.  this is so good, i could live on this salad.  you really need to try this one.  i made my olive salad from scratch this time with both kalamata and green olives, and will continue to go this route.\",\"['iceberg lettuce', 'tomatoes', '3 bean mix', 'olive salad']\",4")
        //When
        mealsCsvReader = MealsCsvReader(fakeFile)
        val result = mealsCsvReader.readCsvFile()

        //Then
        assertThat(result).isNotEmpty()
    }
}