package com.example.s08m03assignment

class Calculator {

    fun addSymbol(symbol: String, number: Int): String {

        val numberAsString = number.toString()

        return "$symbol$numberAsString"
    }

    fun addNumbers(numbersToAdd: List<Int>) : Int {
        return numbersToAdd.sum()
    }

    fun subtractNumbers(number1: Int, number2: Int) : Int {
        return number1 - number2
    }

    fun multiplyNumbers(num1: Int, num2: Int) : Int {
        return num1 * num2
    }

    fun divNumbers(num1: Int, num2: Int) : Int {
        return num1 / num2
    }
}