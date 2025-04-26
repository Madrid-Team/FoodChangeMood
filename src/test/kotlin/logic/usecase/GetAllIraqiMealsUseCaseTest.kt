package logic.usecase

import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import logic.helper.createMeal
import org.junit.jupiter.api.BeforeEach
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
    fun `get all iraqi meals should return all meals containing iraqi in tags or iraq in description`() {

        // Given
        every { repository.getAllMeals() } returns listOf(
            createMeal(
                name = "balalit  saweeya or seviyan  sweet vermicelli breakfast",
                tags = listOf(
                    "iraqi",
                    "sweet"
                ),
                description = "a gluten-free tasty sweet breakfast or dessert made especially during the month of ramadan"

            ),
            createMeal(
                name = "apricot and almond stew with rice",
                tags = listOf(
                    "60-minutes-or-less",
                ),
                description = "\"this is an iraqi dish thought to have originated in iran.\"",
            ),

            createMeal(
                name = "rice",
                tags = listOf("seafood"),
                description = "nono",
            )

        )

        // When

        val result = getAllIraqiMealsUseCase.getAllIraqiMeals()

        // Then
        Truth.assertThat(result).containsExactly(

            createMeal(
                name = "balalit  saweeya or seviyan  sweet vermicelli breakfast",
                tags = listOf(
                    "iraqi",
                    "sweet"
                ),
                description = "a gluten-free tasty sweet breakfast or dessert made especially during the month of ramadan"

            ),
            createMeal(
                name = "apricot and almond stew with rice",
                tags = listOf(
                    "60-minutes-or-less",
                ),
                description = "\"this is an iraqi dish thought to have originated in iran.\"",
            )
        )


    }


    @Test
    fun `get all iraqi meals should return empty list when there are no meals`() {
        // Given
        every { repository.getAllMeals() } returns emptyList()
        //When
        val result = getAllIraqiMealsUseCase.getAllIraqiMeals()
        //Then
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun `get all iraqi meals should return empty list when no meals match iraqi or iraq`() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            createMeal(name = "Pizza", tags = listOf("italian"), description = "Delicious"),
            createMeal(name = "Burger", tags = listOf("fastfood"), description = "Yummy")
        )

        //When
        val result = getAllIraqiMealsUseCase.getAllIraqiMeals()

        //Then
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun `get all iraqi meals should return meals with iraqi tag even if description is null`() {
        every { repository.getAllMeals() } returns listOf(
            createMeal(name = "Soup", tags = listOf("soups"), description = null),
            createMeal(
                name = "null",
                tags = listOf(
                    "iraqi",
                    "sweet"
                )
            )
        )

        val result = getAllIraqiMealsUseCase.getAllIraqiMeals()

        Truth.assertThat(result).containsExactly(
            createMeal(
                name = "null",
                tags = listOf(
                    "iraqi",
                    "sweet"
                )
            )
        )
    }

}
