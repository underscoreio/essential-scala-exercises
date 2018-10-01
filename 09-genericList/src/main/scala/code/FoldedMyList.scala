package code

import scala.annotation.tailrec

// Exercise:
// - Implement foldLeft and foldRight for MyList
// - Reimplement everything else in terms of them!
// - (Optional, Very Hard) Implement foldRight in terms of foldLeft
//   making it stack safe


// This is a variant of CovariantMyList.
// Because the implementations of foldLeft and foldRight are stack-safe,
// everything else is automatically also stack-safe.

sealed trait FoldedMyList[+A] {
  @tailrec
  final def foldLeft[B](accum: B)(func: (B, A) => B): B =
    this match {
      case FoldedMyPair(head, tail) => tail.foldLeft(func(accum, head))(func)
      case FoldedMyNil              => accum
    }

  // We make foldRight tail recursive by implementing it in terms of foldLeft.
  //
  // This is quite subtle. We accumulate a function that,
  // when called by passing it an accumulator,
  // unwinds back up the list folding in the opposite direction.
  def foldRight[B](accum: B)(func: (A, B) => B): B =
    foldLeft[B => B](b => b)((accum, a) => b => accum(func(a, b)))(accum)

  def length: Int =
    foldLeft(0)((b, _) => b + 1)

  def contains[B >: A](item: B): Boolean =
    foldLeft(false)((b, a) => b || a == item)

  def exists[B >: A](func: B => Boolean): Boolean =
    foldLeft(false)((b, a) => b || func(a))

  import FoldedMyList.empty

  def filter(func: A => Boolean): FoldedMyList[A] =
    foldRight(empty[A])((a, b) => if(func(a)) FoldedMyPair(a, b) else b)

  def reverse: FoldedMyList[A] =
    foldLeft(empty[A])((b, a) => FoldedMyPair(a, b))

  def find[B >: A](func: B => Boolean): Option[B] =
    foldLeft(Option.empty[B])((b, a) => b.orElse(Some(a).filter(func)))

  def ++[B >: A](that: FoldedMyList[B]): FoldedMyList[B] =
    foldRight(that)(FoldedMyPair[B])

  def map[B](func: A => B): FoldedMyList[B] =
    foldRight(empty[B])((a, b) => FoldedMyPair(func(a), b))

  def flatMap[B](func: A => FoldedMyList[B]): FoldedMyList[B] =
    foldRight(empty[B])((a, b) => func(a) ++ b)
}

case class FoldedMyPair[A](head: A, tail: FoldedMyList[A]) extends FoldedMyList[A]
case object FoldedMyNil extends FoldedMyList[Nothing]

object FoldedMyList {
  def empty[A]: FoldedMyList[A] =
    FoldedMyNil
}