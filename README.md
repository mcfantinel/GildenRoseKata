# GildenRoseKata
Solution created by Manoel Capaverde (https://www.linkedin.com/in/manoel-capaverde/) for the Gilden Rose Refactoring Kata.

The solution was done based on the TDD (Test-driven development) process. The TDD process was choses because, first of all, I needed to understand the current behavior of the system based on my reading of the requirements. Also I needed to make sure that this behavior would not be compromized by any changes done to the code in the future. After that, I also created new tests for the functionality to be implemented, this new test was supposed to be failing as the feature was not yet implemented.

The refactoring were made and the new functionality created. As I did the tests first, I was confident that I could review the whole code and would not break any previous behavior. This illustrates the power of the TDD process, specially for refactoring and working with legacy code. The tests helped me to fix my code and I introduced a newly refactored version for Gilden Rose with more power for the store expansion, since the new code is more generic while handling items, allowing the system functionality to grow without the need of changing code.

For the code refactor I have used some OOP concepts:
- Object behavior
- Encapsulation
- Inheritance
- Polymorphism
- Code reusage

For approving the tests done to the legacy code, the Text based approval testing was used, comparing the text file generated before the changes to the text file generated after the changes.

To run the test scenarios and have a better look at the code, clone the repository and import the project as a Maven project in any Java IDE.

Programming Language: Java
Build Automation: Maven/Gradle
Unit Test Development: JUnit
Text based approval testing
