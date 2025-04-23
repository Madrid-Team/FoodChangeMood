package logic.usecase

import com.google.common.truth.Truth
import data.models.Ingredients
import data.models.Meal
import data.models.Nutrition
import data.models.Steps
import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
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
        //Given

        // When

        //Then

    }

}
