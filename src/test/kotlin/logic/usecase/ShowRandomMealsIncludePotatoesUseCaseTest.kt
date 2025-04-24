package logic.usecase

import io.mockk.mockk
import logic.Repository.MealsRepository
import org.junit.jupiter.api.BeforeEach

class ShowRandomMealsIncludePotatoesUseCaseTest {
    private lateinit var mealsRepository: MealsRepository
    private lateinit var showRandomMealsIncludePotatoesUseCase: ShowRandomMealsIncludePotatoesUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk()
        showRandomMealsIncludePotatoesUseCase = ShowRandomMealsIncludePotatoesUseCase(mealsRepository)
    }


}