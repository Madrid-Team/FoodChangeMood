package logic.usecase

import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach

class SuggestMealWithHighCalorieUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var useCase: SuggestMealWithHighCalorieUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk(relaxed = true)
        useCase = SuggestMealWithHighCalorieUseCase(mealsRepository)
    }
}
