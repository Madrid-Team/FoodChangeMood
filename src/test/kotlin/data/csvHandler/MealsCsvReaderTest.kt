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
        val file:File = mockk()
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


}