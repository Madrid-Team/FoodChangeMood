package presentation

import data.models.IngredientGameData
import data.models.Meal
import data.utilities.MealsExceptions
import io.mockk.*
import logic.usecase.mealIngredientsGame.GetGameScoreUseCase
import logic.usecase.mealIngredientsGame.GetIngredientGameRandomMealUseCase
import logic.usecase.mealIngredientsGame.MakeGuessUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class MealIngredientsGameUITest {
    private lateinit var gameUI: MealIngredientsGameUI
    private lateinit var getRandomMealUseCase: GetIngredientGameRandomMealUseCase
    private lateinit var makeGuessUseCase: MakeGuessUseCase
    private lateinit var getGameScoreUseCase: GetGameScoreUseCase

    private val standardOut = System.out
    private val outputStream = ByteArrayOutputStream()
    
    @BeforeEach
    fun setUp() {
        // Setup mocks
        getRandomMealUseCase = mockk()
        makeGuessUseCase = mockk()
        getGameScoreUseCase = mockk()
        
        // Redirect stdout
        System.setOut(PrintStream(outputStream))
        
        // Setup Koin
        startKoin {
            modules(module {
                single { getRandomMealUseCase }
                single { makeGuessUseCase }
                single { getGameScoreUseCase }
            })
        }
        
        gameUI = MealIngredientsGameUI()
    }
    
    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
        stopKoin()
        clearAllMocks()
    }
    
    private fun setUserInput(input: String) {
        System.setIn(ByteArrayInputStream(input.toByteArray()))
    }
    
    private fun getOutput() = outputStream.toString().also { outputStream.reset() }
    
    @Test
    fun `test correct guess shows success message and updates score`() {
        // Given
        val mealName = "Pizza Margherita"
        val options = listOf("Tomato", "Basil", "Cheese")
        val correctAnswer = "Basil"
        val score = 1000
        
        // Simple mock setup
        val meal = mockk<Meal>()
        every { meal.name } returns mealName
        
        val gameData = mockk<IngredientGameData>()
        every { gameData.meal } returns meal
        every { gameData.options } returns options
        every { gameData.correctAnswer } returns correctAnswer
        
        every { getRandomMealUseCase() } returns gameData
        every { makeGuessUseCase.invoke(any(), any()) } throws MealsExceptions.GuessMealGamePassed("Great job!")
        every { getGameScoreUseCase() } returns score
        
        // Simulate user selecting option 2 and choosing not to play again
        setUserInput("2\nno\n")
        
        // When
        gameUI.start()
        
        // Then
        val output = getOutput()
        
        // Basic assertions focusing on key elements
        assertTrue(output.contains("The meal is: $mealName"))
        assertTrue(output.contains("1. Tomato"))
        assertTrue(output.contains("2. Basil"))
        assertTrue(output.contains("3. Cheese"))
        assertTrue(output.contains("🏆  Great job!"))
        assertTrue(output.contains("Your score: $score"))
        
        verify(exactly = 1) { getRandomMealUseCase() }
        verify { makeGuessUseCase.invoke(any(), any()) }
        verify { getGameScoreUseCase() }
    }
    
    @Test
    fun `test incorrect guess shows failure message and displays correct answer`() {
        // Given - use simple test values
        val mealName = "Pasta"
        val options = listOf("Salt", "Garlic", "Pepper")
        val correctAnswer = "Pepper"
        
        // Direct mock creation
        val meal = mockk<Meal>()
        every { meal.name } returns mealName
        
        val gameData = mockk<IngredientGameData>()
        every { gameData.meal } returns meal
        every { gameData.options } returns options
        every { gameData.correctAnswer } returns correctAnswer
        
        // Basic setup for useCases
        every { getRandomMealUseCase() } returns gameData
        every { makeGuessUseCase.invoke(any(), any()) } throws 
            MealsExceptions.GuessMealGameNotPassed("Wrong guess!")
        every { getGameScoreUseCase() } returns 0
        
        // Simulate user selecting option 1 and choosing not to play again
        setUserInput("1\nno\n")
        
        // When
        gameUI.start()
        
        // Then
        val output = getOutput()
        
        // Basic assertions focused only on the key messages
        assertTrue(output.contains("The meal is: $mealName"))
        assertTrue(output.contains("✗ Wrong guess!"))
        assertTrue(output.contains("The correct answer was: $correctAnswer"))
        assertTrue(output.contains("Your score: 0"))
        
        // Verify minimal interactions
        verify(exactly = 1) { getRandomMealUseCase() }
        verify { makeGuessUseCase.invoke(any(), any()) }
        verify { getGameScoreUseCase() }
    }
    
    @Test
    fun `test invalid input shows error message`() {
        // Given
        val mealName = "Chicken Curry"
        val options = listOf("Ginger", "Cumin", "Turmeric")
        
        // Simple mock setup
        val meal = mockk<Meal>()
        every { meal.name } returns mealName
        
        val gameData = mockk<IngredientGameData>()
        every { gameData.meal } returns meal
        every { gameData.options } returns options
        
        every { getRandomMealUseCase() } returns gameData
        every { getGameScoreUseCase() } returns 0
        
        // Simulate user entering invalid input and then choosing not to play again
        setUserInput("invalid\nno\n")
        
        // When
        gameUI.start()
        
        // Then
        val output = getOutput()
        
        // Basic assertions
        assertTrue(output.contains("The meal is: $mealName"))
        assertTrue(output.contains("⚠ Please enter a valid number"))
        assertTrue(output.contains("Your score: 0"))

        verify(exactly = 1) { getRandomMealUseCase() }
        verify { getGameScoreUseCase() }
    }
    
    @Test
    fun `test out of range input shows error message`() {
        // Given
        val mealName = "Beef Stew"
        val options = listOf("Carrot", "Potato", "Onion")
        
        // Simple mock setup
        val meal = mockk<Meal>()
        every { meal.name } returns mealName
        
        val gameData = mockk<IngredientGameData>()
        every { gameData.meal } returns meal
        every { gameData.options } returns options
        
        every { getRandomMealUseCase() } returns gameData
        every { getGameScoreUseCase() } returns 0
        
        // Simulate user entering out of range number and then choosing not to play again
        setUserInput("5\nno\n")
        
        // When
        gameUI.start()
        
        // Then
        val output = getOutput()
        
        // Basic assertions
        assertTrue(output.contains("The meal is: $mealName"))
        assertTrue(output.contains("⚠ Invalid number! Please choose between 1 and 3"))
        assertTrue(output.contains("Your score: 0"))

        verify(exactly = 1) { getRandomMealUseCase() }
        verify(exactly = 0) { makeGuessUseCase.invoke(any(), any()) }
        verify { getGameScoreUseCase() }
    }
    
    @Test
    fun `test play again functionality`() {
        // Given - create simple test data
        val firstMealName = "First Meal"
        val secondMealName = "Second Meal"
        
        // First meal setup
        val meal1 = mockk<Meal>()
        every { meal1.name } returns firstMealName
        
        val gameData1 = mockk<IngredientGameData>()
        every { gameData1.meal } returns meal1
        every { gameData1.options } returns listOf("Option1", "Option2", "Option3")
        every { gameData1.correctAnswer } returns "Option2"
        
        // Second meal setup
        val meal2 = mockk<Meal>()
        every { meal2.name } returns secondMealName
        
        val gameData2 = mockk<IngredientGameData>()
        every { gameData2.meal } returns meal2
        every { gameData2.options } returns listOf("Option1", "Option2", "Option3")
        every { gameData2.correctAnswer } returns "Option2"
        
        // Mock use cases for two rounds
        every { getRandomMealUseCase() } returnsMany listOf(gameData1, gameData2)
        every { makeGuessUseCase.invoke(any(), any()) } throws MealsExceptions.GuessMealGamePassed("Great job!")
        every { getGameScoreUseCase() } returnsMany listOf(1, 2)
        
        // Simulate user choosing correct answers and playing twice
        setUserInput("2\nyes\n2\nno\n")
        
        // When
        gameUI.start()
        
        // Then
        val output = getOutput()
        
        // Basic assertions
        assertTrue(output.contains("The meal is: $firstMealName"))
        assertTrue(output.contains("The meal is: $secondMealName"))
        assertTrue(output.contains("Would you like to play again?"))
        assertTrue(output.contains("Thank you for playing!"))
        assertTrue(output.contains("Your score: 1"))
        assertTrue(output.contains("Your score: 2"))
        
        verify(exactly = 2) { getRandomMealUseCase() }
        verify(exactly = 2) { makeGuessUseCase.invoke(any(), any()) }
        verify(exactly = 2) { getGameScoreUseCase() }
    }
}