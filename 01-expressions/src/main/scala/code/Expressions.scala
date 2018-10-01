package code

// Exercise:
//
// In the body of "Expressions" implement the following methods:
//
// - greet
//   - accepts a String name as a parameter
//   - returns a friendly greeting (e.g. "Hello name")
//
// - isPalindrome
//   - accepts a String as a parameter
//   - returns true if it is a palindrome, false otherwise
//
//   Tip: String has a .reverse method: "hello".reverse == "olleh"
//
// - factorial
//   - accepts an Int n as a parameter
//   - returns the factorial of n (1 * 2 * 3 * ... * n)
//
//   Tip: Try multiplying the numbers in reverse order, from n to 1


object Expressions extends Exercise {

  def greet(name: String): String =
    s"Hello $name!"

  def isPalindrome(str: String): Boolean =
    str == str.reverse

  def factorial(n: Int): Int =
    if(n <= 1) n else n * factorial(n - 1)

}
