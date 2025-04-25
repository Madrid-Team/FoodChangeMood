package presentation.features

import logic.usecase.ExploreOtherCountriesFoodUseCase
import presentation.common.BaseUIController
import presentation.common.Reader
import utils.displayMeals
import presentation.common.Viewer

class ExploreOtherCountriesFoodCultureUI(
    private val exploreOtherCountriesFoodUseCase: ExploreOtherCountriesFoodUseCase,
    private val reader: Reader,
    private val viewer: Viewer
) : BaseUIController {
    override val id: Int = 10
    override val message: String = "$id- Enter a country name and you will get up to 20 meals related to this country."

    override fun start() {
        viewer.show("Enter country name you want to search about")
        reader.getUserInput().toString().let { countryName ->
            try {

                exploreOtherCountriesFoodUseCase.getSearchedCountryMeals(countryName).forEach {
                    println(it)
                }

            } catch (exception: Exception) {
                viewer.show("$countryName not found")
            }
        }
    }
}