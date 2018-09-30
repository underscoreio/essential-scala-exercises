package code

object Expressions extends Exercise {

  // Write a method called greet that:
  // - accepts a String name as a parameter
  // - prints a friendly personalised greeting (e.g. "Hello name")
  def greet(name: String): Unit =
    println(s"Hello $name")

  // Write a method called isPalindrome that:
  // - accepts a String as a parameter
  // - returns true if it is a palindrome, false otherwise
  //
  // Tip: String has a .reverse method: "hello".reverse == "olleh"
  def isPalindrome(str: String): Boolean =
    str == str.reverse

  // Write a method called factorial that:
  // - accepts an Int n as a parameter
  // - returns the factorial of n (1 * 2 * 3 * ... * n)
  //
  // Tip: Try multiplying the numbers in reverse order, from n to 1
  def factorial(n: Int): Int =
    if(n <= 1) n else n * factorial(n - 1)

}
