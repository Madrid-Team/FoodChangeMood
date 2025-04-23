package logic.usecase

import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach

class ExploreOtherCountriesFoodUseCaseTest {
    private lateinit var mealsRepository: MealsRepository
    private lateinit var exploreOtherCountriesFoodUseCase: ExploreOtherCountriesFoodUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk()
        exploreOtherCountriesFoodUseCase = ExploreOtherCountriesFoodUseCase(mealsRepository)
    }


}