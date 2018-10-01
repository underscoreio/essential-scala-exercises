package code

import scala.annotation.tailrec

// Exercise 1:
//
// Make MyList generic:
// - give it a type parameter A
// - look for Ints in the codebase and replace them with As
// - if any methods clearly don't transfer across, comment them out
//
//
//
// Exercise 2 (to be done later):
//
// - Make MyList covariant
// - Add a ++ method to append two lists

sealed trait CovariantTailRecMyList[+A] {
  def length: Int = {
    @tailrec
    def loop(list: CovariantTailRecMyList[A], accum: Int): Int =
      list match {
        case CovariantTailRecMyPair(_, t) => loop(t, accum + 1)
        case CovariantTailRecMyNil        => accum
      }

    loop(this, 0)
  }

  @tailrec
  final def contains[B >: A](item: B): Boolean =
    this match {
      case CovariantTailRecMyPair(h, t) => h == item || t.contains(item)
      case CovariantTailRecMyNil        => false
    }

  // This method doesn't transfer because it requires us to know about Ints and addition:
  //  def addToEach(num: Int): CovariantTailRecMyList[A] = {
  //    @tailrec
  //    def loop(list: CovariantTailRecMyList[A], accum: CovariantTailRecMyList[A]): CovariantTailRecMyList[A] =
  //      list match {
  //        case CovariantTailRecMyPair(h, t) => loop(t, CovariantTailRecMyPair(h + num, accum))
  //        case CovariantTailRecMyNil        => accum
  //      }
  //
  //    loop(this, CovariantTailRecMyNil).reverse
  //  }

  // This method doesn't transfer because it requires us to know about Ints and addition:
  //  def sum: Int = {
  //    @tailrec
  //    def loop(list: CovariantTailRecMyList[A], accum: Int): Int =
  //      list match {
  //        case CovariantTailRecMyPair(h, t) => loop(t, h + accum)
  //        case CovariantTailRecMyNil        => accum
  //      }
  //
  //    loop(this, 0)
  //  }

  @tailrec
  final def exists(func: A => Boolean): Boolean =
    this match {
      case CovariantTailRecMyPair(h, t) => func(h) || t.exists(func)
      case CovariantTailRecMyNil        => false
    }

  def filter(func: A => Boolean): CovariantTailRecMyList[A] = {
    @tailrec
    def loop(list: CovariantTailRecMyList[A], accum: CovariantTailRecMyList[A]): CovariantTailRecMyList[A] =
      list match {
        case CovariantTailRecMyPair(h, t) => loop(t, if(func(h)) CovariantTailRecMyPair(h, accum) else accum)
        case CovariantTailRecMyNil        => accum
      }

    loop(this, CovariantTailRecMyNil).reverse
  }

  // To make this tail recursive, we have to accumulate in reverse order and re-reverse the result:
  def reverse: CovariantTailRecMyList[A] = {
    @tailrec
    def loop(list: CovariantTailRecMyList[A], accum: CovariantTailRecMyList[A]): CovariantTailRecMyList[A] =
      list match {
        case CovariantTailRecMyPair(h, t) => loop(t, CovariantTailRecMyPair(h, accum))
        case CovariantTailRecMyNil        => accum
      }

    loop(this, CovariantTailRecMyNil)
  }

  @tailrec
  final def find(func: A => Boolean): Option[A] =
    this match {
      case CovariantTailRecMyPair(h, t) => if(func(h)) Some(h) else t.find(func)
      case CovariantTailRecMyNil        => None
    }

  def ++[B >: A](that: CovariantTailRecMyList[B]): CovariantTailRecMyList[B] = {
    @tailrec
    def loop(list: CovariantTailRecMyList[B], accum: CovariantTailRecMyList[B]): CovariantTailRecMyList[B] =
      list match {
        case CovariantTailRecMyPair(head, tail) => loop(tail, CovariantTailRecMyPair(head, accum))
        case CovariantTailRecMyNil              => accum
      }

    loop(that, loop(this, CovariantTailRecMyNil)).reverse
  }
}

case class CovariantTailRecMyPair[A](head: A, tail: CovariantTailRecMyList[A]) extends CovariantTailRecMyList[A]

case object CovariantTailRecMyNil extends CovariantTailRecMyList[Nothing]

