package code

import org.scalatest._

class ListExpressionsSpec extends FlatSpec with Matchers {
  import ListExpressions._

  "sum" should "sum a list of numbers" in {
    sum(List(1, 2, 3, 4, 5)) should equal(1 + 2 + 3 + 4 + 5)
  }

  it should "return 0 for an empty list" in {
    sum(Nil) should equal(0)
  }

  "contains" should "return true if it finds the number" in {
    contains(List(1, 5, 3, 4, 2), 4) should equal(true)
  }

  it should "return false if the number is missing" in {
    contains(List(1, 5, 3, 2), 4) should equal(false)
  }

  "containsAll" should "return true if it finds all the numbers" in {
    containsAll(List(1, 5, 3, 4, 2), List(2, 3, 4)) should equal(true)
  }

  it should "return false if one of the numbers is missing" in {
    containsAll(List(1, 5, 3, 2), List(2, 3, 4)) should equal(false)
  }

  "squaresUpTo" should "return a list of square numbers" in {
    squareNumbers(10) should contain theSameElementsAs(List(1, 4, 9))
  }

}
