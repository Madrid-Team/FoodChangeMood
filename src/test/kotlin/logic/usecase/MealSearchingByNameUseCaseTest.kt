package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import createMeals
import logic.KMPSearchAlgorithm

class MealSearchingByNameUseCaseTest {
    private lateinit var mealSearchingByNameUseCase: MealSearchingByNameUseCase
    private val mealsRepository: MealsRepository = mockk(relaxed = true)
    private val kmpSearchAlgorithm: KMPSearchAlgorithm = KMPSearchAlgorithm()

    @BeforeEach
    fun setup() {
        mealSearchingByNameUseCase = MealSearchingByNameUseCase(mealsRepository, kmpSearchAlgorithm)
    }

    @Test
    fun `should return meals when search about valid meals`() {
        //given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeals("pasta"),
            createMeals("rice"),
            createMeals("meat")
        )

        //when
        val result = mealSearchingByNameUseCase.searchAboutMealByName("pasta")

        //then
        assertThat(result.map { it.name }).contains("pasta")
    }

    @Test
    fun `should return throw exception when search about not found meals`() {
        //given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeals("pasta"),
            createMeals("rice"),
            createMeals("meat")
        )

        //when && then
        assertThrows<NoSuchElementException> {
            mealSearchingByNameUseCase.searchAboutMealByName("koshary")
        }
    }

    @Test
    fun `should return throw exception when search about empty input`() {
        //given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeals("pasta"),
            createMeals("rice"),
            createMeals("meat")
        )

        //when && then
        assertThrows<NoSuchElementException> {
            mealSearchingByNameUseCase.searchAboutMealByName(" ")
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["pasta", "PASTA", "Pasta", "PasTa"])
    fun `should return meals when searched about meal with upper and lower cases`(searchInput: String) {
        //given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeals("pasta"),
            createMeals("rice"),
            createMeals("meat")
        )

        //when
        val result = mealSearchingByNameUseCase.searchAboutMealByName(searchInput)

        //then
        assertThat(result.map { it.name }).contains("pasta")
    }
}