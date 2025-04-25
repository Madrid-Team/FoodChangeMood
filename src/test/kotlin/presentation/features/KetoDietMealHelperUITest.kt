package presentation.features

import io.mockk.mockk
import logic.usecase.SuggestNewKetoMealUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import presentation.common.ConsoleReader
import presentation.common.ConsoleViewer

class KetoDietMealHelperUITest {

    private lateinit var suggestNewKetoMealUseCase: SuggestNewKetoMealUseCase
    private lateinit var reader: ConsoleReader
    private lateinit var viewer: ConsoleViewer
    private lateinit var ketoDietMealUI: KetoDietMealHelperUI

    @BeforeEach
    fun setUp() {
        suggestNewKetoMealUseCase = mockk(relaxed = true)
        reader = mockk(relaxed = true)
        viewer = mockk(relaxed = true)
        ketoDietMealUI = KetoDietMealHelperUI(suggestNewKetoMealUseCase, reader, viewer)
    }


}