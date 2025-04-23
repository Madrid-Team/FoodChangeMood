package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Date

class SuggestMealWithHighCalorieUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var useCase: SuggestMealWithHighCalorieUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk(relaxed = true)
        useCase = SuggestMealWithHighCalorieUseCase(mealsRepository)
    }

    @Test
    fun `should return high calorie meal when is available`(){
        val meals = listOf(
            Meal(
                name = "",
                id = 1,
                minutes = 1,
                tags = listOf(),
                description = "delicious toast",
                nutrition = Nutrition(450.0, 0.0, 0.0, 0.0, 0.0, 0.0, 50.0),
                steps = Steps(listOf(),0),
                ingredients = Ingredients(listOf(),0),
                submitted = Date(),
                contributorId = 101
            ),
            Meal(
                name = "",
                id = 2,
                minutes = 10,
                tags = listOf(),
                description = "delicious toast",
                nutrition = Nutrition(250.0, 0.0, 0.0, 0.0, 0.0, 0.0, 50.0),
                steps = Steps(listOf(),0),
                ingredients = Ingredients(listOf(),0),
                submitted = Date(),
                contributorId = 102
            ),
            Meal(
                name = "",
                id = 3,
                minutes = 20,
                tags = listOf("", "", ""),
                description = "delicious toast",
                nutrition = Nutrition(850.0, 0.0, 0.0, 0.0, 0.0, 0.0, 50.0),
                steps = Steps(listOf(),0),
                ingredients = Ingredients(listOf(),0),
                submitted = Date(),
                contributorId = 103
            )
        )

        every { mealsRepository.getAllMeals() } returns meals

        val result = useCase.suggestRandomHighCalorieMeal(setOf())

        assertThat(result!!.nutrition.calories).isGreaterThan(700.0)


    }
}
