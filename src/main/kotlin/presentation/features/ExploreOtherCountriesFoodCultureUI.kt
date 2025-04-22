package presentation.features

import logic.usecase.ExploreOtherCountriesFoodUseCase
import presentation.common.BaseUIController

class ExploreOtherCountriesFoodCultureUI(
    private val exploreOtherCountriesFoodUseCase: ExploreOtherCountriesFoodUseCase
) : BaseUIController {
    override val id: Int = 10
    override val message: String = "10- Enter a country name and you will get up to 20 meals related to this country."

    override fun start() {

    }
}