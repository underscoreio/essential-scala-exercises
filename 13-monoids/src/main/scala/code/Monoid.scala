package code

// Exercise:
//
// 1. Create a type class "Monoid[A]" with two operations:
//
//    - a method "combine" that
//      - accepts two parameters of type A
//      - returns a single value of type A
//      - the intention is that implementations will provide some kind of "adding" behaviour
//
//    - a method "empty" that
//      - accepts no parameters
//      - returns a value of type A
//      - the intention is that implementations will provide some kind of "zero" value
//        that provides an identity for "combine" (i.e. X combine 0 == X)
//
// 2. Create a companion object for Monoid containing instances for several types.
//    In each case decide what the implementation of empty and combine should be:
//
//    - Int
//    - Double
//    - String
//    - List
//
//    If you find the boilerplate for writing anonymous classes to be too much,
//    you may want to introduce a helper method that you can use in your implementations.
//
// 3. Create a "summoner" method in the Monoid companion object:
//
//    - it should be called "apply"
//    - it should take one implicit parameter of type Monoid[A]
//    - it should return the parameter
//
//    Now you can summon instances by type without knowing their names, e.g.:
//
//      Monoid[Int] // returns a Monoid[Int] taken from the companion object
//
// 4. Create derived instances of Monoid for other types:
//
//    - Option
//    - Tuple2
//
// 5. Create another object, MonoidSyntax, containing an implicit class MonoidOps[A]
//    that provides the following extension method to any value of a arbitrary type A:
//
//    - a method |+| that
//      - accepts a second value of type A
//      - and an implicit Monoid[A]
//      - and adds the two values together
//
// 6. Create another implicit class in MonoidSyntax, MonoidListOps[A],
//    that provides extension methods to any value of type List[A]:
//
//    - a method combineAll that
//      - accepts an implicit Monoid[A]
//      - folds down the list adding all the elements together
//

trait Monoid[A] {
  def empty: A
  def combine(x: A, y: A): A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]): Monoid[A] =
    monoid

  // Helper method to reduce boilerplate below:
  def create[A](_empty: A)(_combine: (A, A) => A): Monoid[A] =
    new Monoid[A] {
      override def empty: A =
        _empty

      override def combine(x: A, y: A): A =
        _combine(x, y)
    }

  implicit val intMonoid: Monoid[Int] =
    create(0)((a, b) => a + b)

  implicit val doubleMonoid: Monoid[Double] =
    create(0.0)((a, b) => a + b)

  implicit val stringMonoid: Monoid[String] =
    create("")((a, b) => a + b)

  implicit def listMonoid[A]: Monoid[List[A]] =
    create[List[A]](Nil)((a, b) => a ++ b)

  implicit def optionMonoid[A](implicit monoid: Monoid[A]): Monoid[Option[A]] =
    create[Option[A]](None) {
      case (None,    None)    => None
      case (Some(a), None)    => Some(a)
      case (None,    Some(b)) => Some(b)
      case (Some(a), Some(b)) => Some(monoid.combine(a, b))
    }

  implicit def tuple2Monoid[A, B](implicit aMonoid: Monoid[A], bMonoid: Monoid[B]): Monoid[(A, B)] =
    create[(A, B)]((aMonoid.empty, bMonoid.empty)) {
      case ((a1, b1), (a2, b2)) =>
        (aMonoid.combine(a1, a2), bMonoid.combine(b1, b2))
    }
}

object MonoidSyntax {
  implicit class MonoidOps[A](x: A) {
    def |+|(y: A)(implicit monoid: Monoid[A]): A =
      monoid.combine(x, y)
  }

  implicit class MonoidListOps[A](list: List[A]) {
    def combineAll(implicit monoid: Monoid[A]): A =
      list.foldLeft(monoid.empty)(monoid.combine)
  }
}

object MonoidExercise extends Exercise {
  println("EMPTY")
  println(Monoid[Int].empty)
  println(Monoid[Double].empty)
  println(Monoid[String].empty)
  println(Monoid[List[Int]].empty)
  println(Monoid[Option[Int]].empty)
  println(Monoid[(String, Int)].empty)
  println()

  println("COMBINE")
  println(Monoid[Int].combine(1, 2))
  println(Monoid[Double].combine(1.2, 3.4))
  println(Monoid[String].combine("foo", "bar"))
  println(Monoid[List[Int]].combine(List(1, 2), List(3, 4)))
  println(Monoid[Option[Int]].combine(Option(123), Option(456)))
  println(Monoid[(String, Int)].combine(("A", 1), ("B", 2)))
  println()

  import MonoidSyntax._

  println("|+|")
  println(1 |+| 2)
  println(1.2 |+| 3.4)
  println("foo" |+| "bar")
  println(List(1, 2) |+| List(3, 4))
  println(Option(123) |+| Option(456))
  println(("A", 1) |+| (("B", 2)))
  println()

  println("combineAll")
  println(List(1, 2, 3).combineAll)
  println(List(1.2, 3.4, 5.6).combineAll)
  println(List("foo", "bar", "baz").combineAll)
  println(List(Some(123), None, Some(456)).combineAll)
  println(List(("A", 1), ("B", 2), ("C", 3)).combineAll)
  println()
}