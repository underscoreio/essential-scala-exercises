package code

import org.scalatest._

class MonoidSpec extends FlatSpec with Matchers {
  "manually calling empty" should "return a zero element" in {
    Monoid.intMonoid.empty should equal(0)
    Monoid.stringMonoid.empty should equal("")
  }

  "manually calling combine" should "combine elements" in {
    Monoid.intMonoid.combine(1, 2) should equal(3)
    Monoid.stringMonoid.combine("foo", "bar") should equal("foobar")
  }

  "calling empty on a summoned instance" should "return a zero element" in {
    implicitly[Monoid[Int]].empty should equal(0)
    implicitly[Monoid[String]].empty should equal("")
    implicitly[Monoid[Double]].empty should equal(0.0)
    implicitly[Monoid[List[Int]]].empty should equal(Nil)
    implicitly[Monoid[Option[Int]]].empty should equal(None)
    implicitly[Monoid[(String, Int)]].empty should equal(("", 0))
  }

  "calling combine on a summoned instance" should "combine elements" in {
    implicitly[Monoid[Int]].combine(1, 2) should equal(3)
    implicitly[Monoid[String]].combine("foo", "bar") should equal("foobar")
    implicitly[Monoid[Double]].combine(1.2, 3.4) should equal(4.6)
    implicitly[Monoid[List[Int]]].combine(List(1, 2), List(3, 4)) should equal(List(1, 2, 3, 4))
    implicitly[Monoid[Option[Int]]].combine(Option(123), Option(456)) should equal(Some(579))
    implicitly[Monoid[(String, Int)]].combine(("A", 1), ("B", 2)) should equal(("AB", 3))
  }

  "summoner method" should "remove the need to use 'implicirlt'" in {
    Monoid[Int] should equal(implicitly[Monoid[Int]])
    Monoid[String] should equal(implicitly[Monoid[String]])
  }

  import MonoidSyntax._

  "empty syntax" should "return a zero element" in {
    // There's a name collision with something in ScalaTest
    // so we have to refer to this explicitly. D'oh!
    MonoidSyntax.empty[Int] should equal(0)
    MonoidSyntax.empty[String] should equal("")
    MonoidSyntax.empty[Double] should equal(0.0)
    MonoidSyntax.empty[List[Int]] should equal(Nil)
    MonoidSyntax.empty[Option[Int]] should equal(None)
    MonoidSyntax.empty[(String, Int)] should equal(("", 0))
  }

  "combine syntax" should "combine elements" in {
    combine(1, 2) should equal(3)
    combine(1.2, 3.4) should equal(4.6)
    combine("foo", "bar") should equal("foobar")
    combine(List(1, 2), List(3, 4)) should equal(List(1, 2, 3, 4))
    combine(Option(123), Option(456)) should equal(Some(579))
    combine(("A", 1), (("B", 2))) should equal(("AB", 3))
  }

  "|+| syntax" should "combine elements" in {
    1 |+| 2 should equal(3)
    1.2 |+| 3.4 should equal(4.6)
    "foo" |+| "bar" should equal("foobar")
    List(1, 2) |+| List(3, 4) should equal(List(1, 2, 3, 4))
    Option(123) |+| Option(456) should equal(Some(579))
    ("A", 1) |+| (("B", 2)) should equal(("AB", 3))
  }

  "combineAll syntax" should "combine all the things" in {
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
