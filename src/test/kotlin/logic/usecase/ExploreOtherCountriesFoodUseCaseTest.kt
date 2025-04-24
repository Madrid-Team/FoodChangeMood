package logic.usecase

import com.google.common.truth.Truth.assertThat
import createMeals
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ExploreOtherCountriesFoodUseCaseTest {
    private lateinit var mealsRepository: MealsRepository
    private lateinit var exploreOtherCountriesFoodUseCase: ExploreOtherCountriesFoodUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk()
        exploreOtherCountriesFoodUseCase = ExploreOtherCountriesFoodUseCase(mealsRepository)
    }

    @Test
    fun `should return meals related to the country when country name is valid`() {
        //given
        every { mealsRepository.getAllMeals() } returns listOf(
            mealSample
        )

        //when
        val result = exploreOtherCountriesFoodUseCase.getSearchedCountryMeals("Egypt")

        //then
        assertThat(result).contains(mealSample)
    }

    @Test
    fun `should return throw exception when country is not found`() {
        //given
        every { mealsRepository.getAllMeals() } returns listOf(
            mealSample
        )

        //when && then
        assertThrows<NoSuchElementException> {
            exploreOtherCountriesFoodUseCase.getSearchedCountryMeals("Syria")
        }
    }

    @Test
    fun `should return throw exception when list is empty`() {
        //given
        every { mealsRepository.getAllMeals() } returns listOf()

        //when && then
        assertThrows<NoSuchElementException> {
            exploreOtherCountriesFoodUseCase.getSearchedCountryMeals("Syria")
        }
    }


    @Test
    fun `should return throw exception when input is empty or blank`() {
        //given
        every { mealsRepository.getAllMeals() } returns listOf(
            mealSample
        )

        //when && then
        assertThrows<NoSuchElementException> {
            exploreOtherCountriesFoodUseCase.getSearchedCountryMeals("")
        }
    }


    companion object {
        private const val name = "egyptian lentils rice and  macaroni"
        private const val description =
            "although a vegetarian dish, this gets eaten with reckless abandon by" +
                    "carnivores.it sounds way too healthy (and it is) but dang! it is good. " +
                    "try it. give about 45 minutes from start to finish.if you don't have cooked brown rice lying around, start that first and foremost." +
                    "(this is modified from a recipe from joy of cooking and embellished with touches " +
                    "i figured out from an egyptian restaurant in washington dc)."
        private val tags =
            listOf(
                "60-minutes-or-less",
                "time-to-make",
                "course",
                "main-ingredient",
                "cuisine",
                "preparation",
                "main-dish",
                "side-dishes",
                "beans",
                "pasta",
                "rice",
                "asian",
                "middle-eastern",
                "vegetarian",
                "dietary",
                "lentils",
                "pasta-rice-and-grains",
                "elbow-macaroni",
                "brown-rice"
            )
        private val mealSample = createMeals(
            name = this.name,
            description = this.description,
            tags = this.tags
        )
    }
}