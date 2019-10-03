package com

import com.example.s08m03assignment.Calculator
import org.junit.Test
import kotlin.test.assertEquals

class CalculatorUnitTest {

    companion object {
        private val calculator = Calculator()
    }

    @Test
    fun addSymbolsTest_CastSymbolToNonInitString() {

        // SETUP
        val string: String
        val number = 5
        val symbol = "$"
        val expected = "$5"

        // EXECUTE
        string = calculator.addSymbol(symbol, number)

        // CHECK
        assertEquals(expected, string)
    }

    @Test
    fun addSymbolsTest_ConcatSymbolWithMultStrings() {
        // SETUP
        val string1 = "The price for this item is"
        val symbol = "$"
        val number = 38
        val expected = "The price for this item is $38"
        val concatenatedString: String

        // EXECUTE
        concatenatedString = "$string1 ${calculator.addSymbol(symbol, number)}"

        // CHECK
        assertEquals(expected, concatenatedString)


    }

    @Test
    fun addNumbersTest_AddTwoNumbers() {
        // SETUP
        val numbersToAdd = listOf(
            1,13
        )
        val expected = 14
        val sum: Int


        //EXECUTE
        sum = calculator.addNumbers(numbersToAdd)

        // CHECK
        assertEquals(expected, sum)

    }

    @Test
    fun addNumbersTest_AddMultipleNumbers() {

        // SETUP
        val numbersToAdd = listOf(
            14, 12, 18, 32
        )
        val sum: Int
        val expected = 76
        val calculator = Calculator()

        // EXECUTE
        sum = calculator.addNumbers(numbersToAdd)

        // CHECK
        assertEquals(expected, sum)
    }

    @Test
    fun subtractNumbersTest() {
        //SETUP
        val number1 = 20
        val number2 = 19
        val difference: Int
        val expected = 1

        //EXECUTE
        difference = calculator.subtractNumbers(number1, number2)

        // CHECK
        assertEquals(expected, difference)
    }

    @Test
    fun multiplyNumbersTest() {
        // SETUP
        val number1 = 3
        val number2 = 4
        val product: Int
        val expected = 12

        // EXECUTE
        product = calculator.multiplyNumbers(number1, number2)

        // CHECK
        assertEquals(expected, product)
    }

    @Test
    fun divNumbersTest() {

        //SETUP
        val number1 = 4
        val number2 = 2
        val result: Int
        val expected = 2

        // EXECUTE
        result = calculator.divNumbers(number1,number2)

        // CHECK
        assertEquals(expected, result)
    }
}