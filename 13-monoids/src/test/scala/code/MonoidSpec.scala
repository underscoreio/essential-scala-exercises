package code

import org.scalatest._

class MonoidSpec extends FlatSpec with Matchers {

  "empty" should "return a zero element" in {
    Monoid[Int].empty should equal(0)
    Monoid[Double].empty should equal(0.0)
    Monoid[String].empty should equal("")
    Monoid[List[Int]].empty should equal(Nil)
    Monoid[Option[Int]].empty should equal(None)
    Monoid[(String, Int)].empty should equal(("", 0))
  }

  "combine" should "combine elements" in {
    Monoid[Int].combine(1, 2) should equal(3)
    Monoid[Double].combine(1.2, 3.4) should equal(4.6)
    Monoid[String].combine("foo", "bar") should equal("foobar")
    Monoid[List[Int]].combine(List(1, 2), List(3, 4)) should equal(List(1, 2, 3, 4))
    Monoid[Option[Int]].combine(Option(123), Option(456)) should equal(Some(579))
    Monoid[(String, Int)].combine(("A", 1), ("B", 2)) should equal(("AB", 3))
  }

  import MonoidSyntax._

  "|+|" should "combine elements" in {
    1 |+| 2 should equal(3)
    1.2 |+| 3.4 should equal(4.6)
    "foo" |+| "bar" should equal("foobar")
    List(1, 2) |+| List(3, 4) should equal(List(1, 2, 3, 4))
    Option(123) |+| Option(456) should equal(Some(579))
    ("A", 1) |+| (("B", 2)) should equal(("AB", 3))
  }

  "combineAll" should "combine all the things" in {
    List(1, 2, 3).combineAll should equal(6)
    List(1.2, 3.4, 5.6).combineAll should equal(10.2)
    List("foo", "bar", "baz").combineAll should equal("foobarbaz")
    List(Some(123), None, Some(456)).combineAll should equal(Some(579))
    List(("A", 1), ("B", 2), ("C", 3)).combineAll should equal(("ABC", 6))
  }

  it should "work with an empty list" in {
    List.empty[Int].combineAll should equal(0)
    List.empty[Double].combineAll should equal(0.0)
    List.empty[String].combineAll should equal("")
    List.empty[List[Int]].combineAll should equal(Nil)
    List.empty[Option[Int]].combineAll should equal(None)
    List.empty[(String, Int)].combineAll should equal(("", 0))
  }
}
