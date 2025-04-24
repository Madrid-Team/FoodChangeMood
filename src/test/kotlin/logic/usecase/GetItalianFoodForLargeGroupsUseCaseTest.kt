package logic.usecase

import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach

class GetItalianFoodForLargeGroupsUseCaseTest {
    private lateinit var mealRepository: MealsRepository
    private lateinit var getItalianFoodForLargeGroupsUseCase: GetItalianFoodForLargeGroupsUseCase

    @BeforeEach
    fun setup(){
        mealRepository = mockk(relaxed = true)
        getItalianFoodForLargeGroupsUseCase = GetItalianFoodForLargeGroupsUseCase(mealRepository)

    }




}