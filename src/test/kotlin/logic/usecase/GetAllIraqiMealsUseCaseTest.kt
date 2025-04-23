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
        // Given
        val fixedDate = Date()
        every { repository.getAllMeals() } returns listOf(
            Meal(
                name = "balalit  saweeya or seviyan  sweet vermicelli breakfast",
                id = 361328,
                minutes = 43,
                tags = listOf(
                    "lactose",
                    "60-minutes-or-less",
                    "time-to-make",
                    "course",
                    "main-ingredient",
                    "cuisine",
                    "preparation",
                    "occasion",
                    "iraqi",
                    "saudi-arabian",
                    "breakfast",
                    "desserts",
                    "pasta",
                    "asian",
                    "middle-eastern",
                    "easy",
                    "holiday-event",
                    "dietary",
                    "gluten-free",
                    "ramadan",
                    "egg-free",
                    "free-of-something",
                    "pasta-rice-and-grains",
                    "taste-mood",
                    "sweet"
                ),
                description = "a gluten-free tasty sweet breakfast or dessert made especially during the month of ramadan in the arabian gulf - kuwait, uae, oman... sometimes made with onion and egg. originally published on http://arabicbites.blogspot.com.",
                nutrition = Nutrition(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                steps = Steps(emptyList(), 1),
                ingredients = Ingredients(emptyList(), 3),
                submitted = fixedDate,
                contributorId = 210
            ),
            Meal(
                name = "apricot and almond stew with rice",
                id = 420605,
                minutes = 35,
                tags = listOf(
                    "60-minutes-or-less",
                    "time-to-make",
                    "course",
                    "main-ingredient",
                    "preparation",
                    "low-protein",
                    "healthy",
                    "main-dish",
                    "fruit",
                    "vegetables",
                    "vegan",
                    "vegetarian",
                    "nuts",
                    "dietary",
                    "low-sodium",
                    "low-cholesterol",
                    "low-saturated-fat",
                    "inexpensive",
                    "healthy-2",
                    "low-in-something",
                    "onions",
                    "number-of-servings"
                ),
                description = "\"this is an iraqi dish thought to have originated in iran.  it\u0027s tangy and sweet and is a special treat in my family.  i have not included the directions for making the rice as i sometimes use a rice cooker and at other times i make it steamed the traditional way.  either way is fine.sour salt, also called citric acid crystals looks like sugar and can be found in most stores.  careful, it is very potent.the weight of the apricots is a guess.  it is one box or bag of dried apricots as packaged in the stores.\"",
                nutrition = Nutrition(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                steps = Steps(emptyList(), 1),
                ingredients = Ingredients(emptyList(), 3),
                submitted = fixedDate,
                contributorId = 210
            ),
            Meal(
                name = "ba7 7ari   meat with rice  bahrain",
                id = 477472,
                minutes = 70,
                tags = listOf(
                    "weeknight",
                    "time-to-make",
                    "course",
                    "main-ingredient",
                    "cuisine",
                    "preparation",
                    "occasion",
                    "iraqi",
                    "saudi-arabian",
                    "main-dish",
                    "lamb-sheep",
                    "rice",
                    "middle-eastern",
                    "easy",
                    "dietary",
                    "one-dish-meal",
                    "gluten-free",
                    "egg-free",
                    "free-of-something",
                    "meat",
                    "pasta-rice-and-grains",
                    "long-grain-rice",
                    "taste-mood",
                    "savory",
                    "4-hours-or-less"
                ),
                description = "\"for welcome to bahrain tag game! \"\"this is a traditional bahraini dish that is quite easy to make. ba7ari comes from the root word ba7ar which means sea. i\u0027m not sure why its called this...\"\" modified from a recipe found on, http://6abkhatummi.blogspot.ca\"",
                nutrition = Nutrition(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                steps = Steps(emptyList(), 1),
                ingredients = Ingredients(emptyList(), 3),
                submitted = fixedDate,
                contributorId = 210
            ),
            Meal(
                name = "rice",
                id = 20,
                minutes = 10,
                tags = listOf("seafood"),
                description = "nono",
                nutrition = Nutrition(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                steps = Steps(emptyList(), 1),
                ingredients = Ingredients(emptyList(), 3),
                submitted = fixedDate,
                contributorId = 210
            ),
            Meal(
                name = "dolma  stuffed grape leaves   iraqi style",
                id = 380480,
                minutes = 120,
                tags = listOf(
                    "lactose",
                    "weeknight",
                    "time-to-make",
                    "course",
                    "main-ingredient",
                    "cuisine",
                    "preparation",
                    "occasion",
                    "iraqi",
                    "main-dish",
                    "beef",
                    "lamb-sheep",
                    "rice",
                    "asian",
                    "greek",
                    "middle-eastern",
                    "european",
                    "finger-food",
                    "stove-top",
                    "lebanese",
                    "dietary",
                    "one-dish-meal",
                    "comfort-food",
                    "egg-free",
                    "free-of-something",
                    "meat",
                    "pasta-rice-and-grains",
                    "long-grain-rice",
                    "taste-mood",
                    "equipment",
                    "number-of-servings",
                    "presentation",
                    "served-cold",
                    "served-hot",
                    "4-hours-or-less"
                ),
                description = "this middle eastern dish is a family favorite. the blend of the spices coupled with the tartness of the lemon makes it out of this world. this recipe has been passed down in my family for many years.",
                nutrition = Nutrition(200.0, 10.0, 5.0, 300.0, 2.0, 25.0, 7.0),
                steps = Steps(listOf("cook", "serve"), 2),
                ingredients = Ingredients(listOf("rice", "leaves"), 2),
                submitted = fixedDate,
                contributorId = 1
            ),
            Meal(
                name = "Burger",
                id = 102,
                minutes = 20,
                tags = listOf("fastfood"),
                description = "Fast food from USA",
                nutrition = Nutrition(500.0, 20.0, 8.0, 600.0, 5.0, 60.0, 10.0),
                steps = Steps(listOf("cook", "eat"), 2),
                ingredients = Ingredients(listOf("bun", "meat"), 2),
                submitted = fixedDate,
                contributorId = 2
            )


        )

        // When

        val result = getAllIraqiMealsUseCase.getAllIraqiMeals()

        // Then
        Truth.assertThat(result).containsExactly(

            Meal(
                name = "balalit  saweeya or seviyan  sweet vermicelli breakfast",
                id = 361328,
                minutes = 43,
                tags = listOf(
                    "lactose",
                    "60-minutes-or-less",
                    "time-to-make",
                    "course",
                    "main-ingredient",
                    "cuisine",
                    "preparation",
                    "occasion",
                    "iraqi",
                    "saudi-arabian",
                    "breakfast",
                    "desserts",
                    "pasta",
                    "asian",
                    "middle-eastern",
                    "easy",
                    "holiday-event",
                    "dietary",
                    "gluten-free",
                    "ramadan",
                    "egg-free",
                    "free-of-something",
                    "pasta-rice-and-grains",
                    "taste-mood",
                    "sweet"
                ),
                description = "a gluten-free tasty sweet breakfast or dessert made especially during the month of ramadan in the arabian gulf - kuwait, uae, oman... sometimes made with onion and egg. originally published on http://arabicbites.blogspot.com.",
                nutrition = Nutrition(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                steps = Steps(emptyList(), 1),
                ingredients = Ingredients(emptyList(), 3),
                submitted = fixedDate,
                contributorId = 210
            ),
            Meal(
                name = "apricot and almond stew with rice",
                id = 420605,
                minutes = 35,
                tags = listOf(
                    "60-minutes-or-less",
                    "time-to-make",
                    "course",
                    "main-ingredient",
                    "preparation",
                    "low-protein",
                    "healthy",
                    "main-dish",
                    "fruit",
                    "vegetables",
                    "vegan",
                    "vegetarian",
                    "nuts",
                    "dietary",
                    "low-sodium",
                    "low-cholesterol",
                    "low-saturated-fat",
                    "inexpensive",
                    "healthy-2",
                    "low-in-something",
                    "onions",
                    "number-of-servings"
                ),
                description = "\"this is an iraqi dish thought to have originated in iran.  it\u0027s tangy and sweet and is a special treat in my family.  i have not included the directions for making the rice as i sometimes use a rice cooker and at other times i make it steamed the traditional way.  either way is fine.sour salt, also called citric acid crystals looks like sugar and can be found in most stores.  careful, it is very potent.the weight of the apricots is a guess.  it is one box or bag of dried apricots as packaged in the stores.\"",
                nutrition = Nutrition(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                steps = Steps(emptyList(), 1),
                ingredients = Ingredients(emptyList(), 3),
                submitted = fixedDate,
                contributorId = 210
            ),
            Meal(
                name = "ba7 7ari   meat with rice  bahrain",
                id = 477472,
                minutes = 70,
                tags = listOf(
                    "weeknight",
                    "time-to-make",
                    "course",
                    "main-ingredient",
                    "cuisine",
                    "preparation",
                    "occasion",
                    "iraqi",
                    "saudi-arabian",
                    "main-dish",
                    "lamb-sheep",
                    "rice",
                    "middle-eastern",
                    "easy",
                    "dietary",
                    "one-dish-meal",
                    "gluten-free",
                    "egg-free",
                    "free-of-something",
                    "meat",
                    "pasta-rice-and-grains",
                    "long-grain-rice",
                    "taste-mood",
                    "savory",
                    "4-hours-or-less"
                ),
                description = "\"for welcome to bahrain tag game! \"\"this is a traditional bahraini dish that is quite easy to make. ba7ari comes from the root word ba7ar which means sea. i\u0027m not sure why its called this...\"\" modified from a recipe found on, http://6abkhatummi.blogspot.ca\"",
                nutrition = Nutrition(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                steps = Steps(emptyList(), 1),
                ingredients = Ingredients(emptyList(), 3),
                submitted = fixedDate,
                contributorId = 210
            ),
            Meal(
                name = "dolma  stuffed grape leaves   iraqi style",
                id = 380480,
                minutes = 120,
                tags = listOf(
                    "lactose",
                    "weeknight",
                    "time-to-make",
                    "course",
                    "main-ingredient",
                    "cuisine",
                    "preparation",
                    "occasion",
                    "iraqi",
                    "main-dish",
                    "beef",
                    "lamb-sheep",
                    "rice",
                    "asian",
                    "greek",
                    "middle-eastern",
                    "european",
                    "finger-food",
                    "stove-top",
                    "lebanese",
                    "dietary",
                    "one-dish-meal",
                    "comfort-food",
                    "egg-free",
                    "free-of-something",
                    "meat",
                    "pasta-rice-and-grains",
                    "long-grain-rice",
                    "taste-mood",
                    "equipment",
                    "number-of-servings",
                    "presentation",
                    "served-cold",
                    "served-hot",
                    "4-hours-or-less"
                ),
                description = "this middle eastern dish is a family favorite. the blend of the spices coupled with the tartness of the lemon makes it out of this world. this recipe has been passed down in my family for many years.",
                nutrition = Nutrition(200.0, 10.0, 5.0, 300.0, 2.0, 25.0, 7.0),
                steps = Steps(listOf("cook", "serve"), 2),
                ingredients = Ingredients(listOf("rice", "leaves"), 2),
                submitted = fixedDate,
                contributorId = 1
            )
        )


    }

}
