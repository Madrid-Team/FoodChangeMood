package logic.usecase

import io.mockk.every
import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class GetItalianFoodForLargeGroupsUseCaseTest {
    private lateinit var mealRepository: MealsRepository
    private lateinit var getItalianFoodForLargeGroupsUseCase: GetItalianFoodForLargeGroupsUseCase

    @BeforeEach
    fun setup(){
        mealRepository = mockk(relaxed = true)
        getItalianFoodForLargeGroupsUseCase = GetItalianFoodForLargeGroupsUseCase(mealRepository)

    }

    @Test
    fun `should return valid meals when the list has italian food for large groups`(){
        //Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMealItalianLargeGroups("pasta" ,2 , tags = listOf())




        )


        //when



        //then




    }






}