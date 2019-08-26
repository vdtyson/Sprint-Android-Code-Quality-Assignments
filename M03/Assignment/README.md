# Android Test Driven Design Calculator

For this app, you'll bulid a simple, basic calculator. The difference, is that you'll be using Test Driven design to do it.
> You will also not be building a UI for this as we will be using TDD to do that for the next assignment.

## Steps

1. Build a `Calculator` class with an empty constructor and a method called `addSymbol` or something to that effect which accepts and returns a string. For now it will just return the accepted string.
> Don't implement this now, but This method will be called when a symbol button is pressed (number or decimal), it will add that symbol to its internal member tracking the number entered into the calculator and then return a string representation of that number to be displayed

2. Write 2 tests, 1 to test if the method works when used once to add a single character when nothing else has been added, and 1 to test when multiple consecutive strings are added.  Run the tests.
> The first test should pass, but the second test should fail.

3. Implement that method in your calculator class.
4. Continue in this manner. Build a test for a step you want to perform, say, adding a decimal. Think of all the potential use cases to cover ie: adding a single decimal, what if the number already has a decimal. Since the Calculator is part of a separate class, it shouldn't manipulate any UI instead, return the desired string to be displayed.

This calculator should have the following features:
* Add numbers (in the future, you'll add them with buttons 0-9), but for now, just call the methods manually
* Add a decimal to the current number
* Remove the most recently added character (backspace)
* Perform the following calculations
	* Addition
	* Subraction
	* Multiplication
	* Division
* Perform repeated calculations (5 * 10 = 50, pressing equals again should return 500, etc.)
* Clear all values

Be sure to add test cases for each use case on each feature before implementing that feature

#### Go Further

* Add more functions to your calculator (square, square root, pi)
