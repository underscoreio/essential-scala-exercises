package code

import org.scalatest._

class ExpressionsSpec extends FlatSpec with Matchers {
  import Expressions._

  "tacocat" should "be a palindrome" in {
    pending
    // isPalindrome("tacocat") should equal(true)
  }

  "tacobell" should "NOT be a palindrome" in {
    pending
    // isPalindrome("tacobell") should equal(false)
  }

  "greet" should "return a greeting (not print it)" in {
    pending
    // greet("Dave") should equal("Hello Dave!")
  }

  "factorial" should "multiply some numbers and stuff" in {
    pending
    // factorial(3) should equal(3 * 2 * 1)
    // factorial(3) should equal(3 * 2 * 1)
    // factorial(5) should equal(5 * 4 * 3 * 2 * 1)
  }
}
