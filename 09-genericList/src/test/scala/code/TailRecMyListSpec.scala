package code

import org.scalatest._

class TailRecMyListSpec extends FlatSpec with Matchers {

  // Helper method to make it easier to build lists.
  // You'll see how this works in the Sequencing Computations section:
  def list[A](items: A *): TailRecMyList[A] =
    items.foldRight[TailRecMyList[A]](TailRecMyNil)(TailRecMyPair[A])

  "length" should "return the length of the list" in {
    list().length should equal(0)
    list(1, 2, 3).length should equal(3)
    list(1, 2, 3, 4, 5).length should equal(5)

    list().length should equal(0)
    list("1", "2", "3").length should equal(3)
    list("1", "2", "3", "4", "5").length should equal(5)
  }

  "contains" should "return true and false appropriately" in {
    list(1, 2, 3).contains(1) should equal(true)
    list(1, 2, 3).contains(2) should equal(true)
    list(1, 2, 3).contains(5) should equal(false)
    list().contains(1) should equal(false)

    list("1", "2", "3").contains("1") should equal(true)
    list("1", "2", "3").contains("2") should equal(true)
    list("1", "2", "3").contains("5") should equal(false)
    list().contains("1") should equal(false)
  }

  //  "addToEach" should "increment every element in the list" in {
  //    list(1, 2, 3).addToEach(1) should equal(list(2, 3, 4))
  //    list(1, 2, 3).addToEach(5) should equal(list(6, 7, 8))
  //
  //    list().addToEach(1) should equal(list())
  //
  //    list(1, 2, 3).addToEach(0) should equal(list(1, 2, 3))
  //    list(1, 2, 3).addToEach(-1) should equal(list(0, 1, 2))
  //  }

  //  "sum" should "sum all elements" in {
  //    list(1, 2, 3).sum should equal(6)
  //    list(1, 2, 3, 4).sum should equal(10)
  //
  //    list().sum should equal(0)
  //  }

  "exists" should "find elements by predicate" in {
    list(1, 2, 3).exists(n => n == 2) should equal(true)
    list(1, 2, 3).exists(n => n < 1) should equal(false)

    list("1", "2", "3").exists(n => n == "2") should equal(true)
    list("1", "2", "3").exists(n => n == "4") should equal(false)

    list().exists((_: Any) => true) should equal(false)
  }

  "filter" should "filter elements by predicate" in {
    list(1, 2, 3).filter(n => n == 2) should equal(list(2))
    list(1, 2, 3).filter(n => n % 2 == 1) should equal(list(1, 3))
    list(1, 2, 3).filter(n => n < 1) should equal(list())

    list("1", "2", "3").filter(n => n == "2") should equal(list("2"))
    list("1", "2", "3").filter(n => n == "1" || n == "3") should equal(list("1", "3"))
    list("1", "2", "3").filter(n => n == "4") should equal(list())

    list().filter((_: Any) => true) should equal(list())
  }

  "reverse" should "reverse the list" in {
    list(1, 2, 3).reverse should equal(list(3, 2, 1))

    list("1", "2", "3").reverse should equal(list("3", "2", "1"))

    list[Any]().reverse should equal(list())
  }

  "find" should "find a single item in the list" in {
    list(1, 2, 3).find(n => n > 1) should equal(Some(2))
    list(1, 2, 3).find(n => n < 1) should equal(None)

    list("1", "2", "3").find(n => n == "2") should equal(Some("2"))
    list("1", "2", "3").find(n => n != "2") should equal(Some("1"))

    list().find((_: Any) => true) should equal(None)
  }

  "append" should "append two lists" in {
    list(1, 2) ++ list(3, 4) should equal(list(1, 2, 3, 4))
    list("1", "2") ++ list("3", "4") should equal(list("1", "2", "3", "4"))
  }
}
