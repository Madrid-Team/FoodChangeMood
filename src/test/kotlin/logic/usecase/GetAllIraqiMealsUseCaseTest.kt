package logic.usecase

import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import logic.helper.createMeal
import org.junit.jupiter.api.BeforeEach
import java.util.*
import kotlin.test.Test

class GetAllIraqiMealsUseCaseTest() {
    private lateinit var repository: MealsRepository
    private lateinit var getAllIraqiMealsUseCase: GetAllIraqiMealsUseCase

    @BeforeEach
    fun setup() {
        repository = mockk(relaxed = true)
        getAllIraqiMealsUseCase = GetAllIraqiMealsUseCase(repository)
    }


    @Test
    fun `should return all meals containing iraqi in tags or description`() {
        // Given
        val fixedDate = Date()
        every { repository.getAllMeals() } returns listOf(
            createMeal(
                name = "balalit  saweeya or seviyan  sweet vermicelli breakfast",
                tags = listOf(
                    "lactose",
                    "occasion",
                    "iraqi",
                    "sweet"
                ),
                description = "a gluten-free tasty sweet breakfast or dessert made especially during the month of ramadan"

            ),
            createMeal(
                name = "apricot and almond stew with rice",
                tags = listOf(
                    "60-minutes-or-less",
                    "time-to-make",
                    "course",
                ),
                description = "\"this is an iraqi dish thought to have originated in iran.\"",
            ),
            createMeal(
                name = "ba7 7ari   meat with rice  bahrain",
                tags = listOf(
                    "occasion",
                    "iraqi",
                    "saudi-arabian",
                    "middle-eastern",

                    ),
                description = "is a traditional bahraini dish that is quite easy to make."
            ),
            createMeal(
                name = "rice",
                tags = listOf("seafood"),
                description = "nono",
            ),
            createMeal(
                name = "dolma  stuffed grape leaves   iraqi style",
                tags = listOf(
                    "preparation",
                    "occasion",
                    "iraqi",
                    "main-dish",
                    "beef",
                ),
                description = "this middle eastern dish is a family favorite."
            ),
            createMeal(
                name = "Burger",
                tags = listOf("fastfood"),
                description = "Fast food from USA"
            )

        )

        // When

        val result = getAllIraqiMealsUseCase.getAllIraqiMeals()

        // Then
        Truth.assertThat(result).containsExactly(

            createMeal(
                name = "balalit  saweeya or seviyan  sweet vermicelli breakfast",
                tags = listOf(
                    "lactose",
                    "occasion",
                    "iraqi",
                    "sweet"
                ),
                description = "a gluten-free tasty sweet breakfast or dessert made especially during the month of ramadan"

            ),
            createMeal(
                name = "apricot and almond stew with rice",
                tags = listOf(
                    "60-minutes-or-less",
                    "time-to-make",
                    "course",
                ),
                description = "\"this is an iraqi dish thought to have originated in iran.\"",
            ),
            createMeal(
                name = "ba7 7ari   meat with rice  bahrain",
                tags = listOf(
                    "occasion",
                    "iraqi",
                    "saudi-arabian",
                    "middle-eastern",

                    ),
                description = "is a traditional bahraini dish that is quite easy to make."
            ),
            createMeal(
                name = "dolma  stuffed grape leaves   iraqi style",
                tags = listOf(
                    "preparation",
                    "occasion",
                    "iraqi",
                    "main-dish",
                    "beef",
                ),
                description = "this middle eastern dish is a family favorite."
            )
        )


    }

}
