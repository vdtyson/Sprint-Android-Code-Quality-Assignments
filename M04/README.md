# Android UI Testing Calculator

For this app, you'll build the UI for your calculator using the same test driven design principles as before.

## Instructions

- Create a test in androidTest

- Build the XML for the UI

- Add use cases for every user interaction you will support before you add UI to support it.

  - Build a test `shouldAdd9ToUI` then add a click event to the button to handle that (be sure to use the code you wrote in the Calculator class to do this)

    

Remember, this calculator should have the following features:

- Add numbers (in the future, you'll add them with buttons 0-9), but for now, just call the methods manually
- Add a decimal to the current number
- Remove the most recently added character (backspace)
- Perform the following calculations
  - Addition
  - Subraction
  - Multiplication
  - Division
- Perform repeated calculations (5 * 10 = 50, pressing equals again should return 500, etc.)
- Clear all values

Be sure to add test cases for each use case on each feature before implementing that feature

#### Go Further

- Use what we've learned about styles and themes to make the app look how you want it to.