package code

import org.scalatest._

class IntListSpec extends FlatSpec with Matchers {

  // Helper method to make it easier to build lists.
  // You'll see how this works in the Sequencing Computations section:
  // def intList(ints: Int *): IntList =
  //   ints.foldRight[IntList](IntNil)(IntPair)

  "length" should "return the length of the list" in {
    pending
    // intList().length should equal(0)
    // intList(1, 2, 3).length should equal(3)
    // intList(1, 2, 3, 4, 5).length should equal(5)
  }

  "contains" should "return true and false appropriately" in {
    pending
    // intList(1, 2, 3).contains(1) should equal(true)
    // intList(1, 2, 3).contains(2) should equal(true)
    // intList(1, 2, 3).contains(5) should equal(false)
    // intList().contains(1) should equal(false)
  }

  "addToEach" should "increment every element in the list" in {
    pending
    // intList(1, 2, 3).addToEach(1) should equal(intList(2, 3, 4))
    // intList(1, 2, 3).addToEach(5) should equal(intList(6, 7, 8))

    // intList().addToEach(1) should equal(intList())

    // intList(1, 2, 3).addToEach(0) should equal(intList(1, 2, 3))
    // intList(1, 2, 3).addToEach(-1) should equal(intList(0, 1, 2))
  }

  "sum" should "sum all elements" in {
    pending
    // intList(1, 2, 3).sum should equal(6)
    // intList(1, 2, 3, 4).sum should equal(10)

    // intList().sum should equal(0)
  }

  "exists" should "find elements by predicate" in {
    pending
    // intList(1, 2, 3).exists(n => n == 2) should equal(true)
    // intList(1, 2, 3).exists(n => n < 1) should equal(false)

    // intList().exists(_ => true) should equal(false)
  }

  "filter" should "filter elements by predicate" in {
    pending
    // intList(1, 2, 3).filter(n => n == 2) should equal(intList(2))
    // intList(1, 2, 3).filter(n => n % 2 == 1) should equal(intList(1, 3))
    // intList(1, 2, 3).filter(n => n < 1) should equal(intList())

    // intList().filter(_ => true) should equal(intList())
  }

  "reverse" should "reverse the list" in {
    pending
    // intList(1, 2, 3).reverse should equal(intList(3, 2, 1))

    // intList().reverse should equal(intList())
  }

  "find" should "find a single item in the list" in {
    pending
    // intList(1, 2, 3).find(n => n > 1) should equal(Some(2))

    // intList(1, 2, 3).find(n => n < 1) should equal(None)

    // intList().find(_ => true) should equal(None)
  }
}
