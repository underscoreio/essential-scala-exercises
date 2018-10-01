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

sealed trait TailRecMyList[A] {
  def length: Int = {
    @tailrec
    def loop(list: TailRecMyList[A], accum: Int): Int =
      list match {
        case TailRecMyPair(_, t) => loop(t, accum + 1)
        case TailRecMyNil()      => accum
      }

    loop(this, 0)
  }

  @tailrec
  final def contains(item: A): Boolean =
    this match {
      case TailRecMyPair(h, t) => h == item || t.contains(item)
      case TailRecMyNil()      => false
    }

  // This method doesn't transfer because it requires us to know about Ints and addition:
  //  def addToEach(num: Int): TailRecMyList[A] = {
  //    @tailrec
  //    def loop(list: TailRecMyList[A], accum: TailRecMyList[A]): TailRecMyList[A] =
  //      list match {
  //        case TailRecMyPair(h, t) => loop(t, TailRecMyPair(h + num, accum))
  //        case TailRecMyNil()      => accum
  //      }
  //
  //    loop(this, TailRecMyNil()).reverse
  //  }

  // This method doesn't transfer because it requires us to know about Ints and addition:
  //  def sum: Int = {
  //    @tailrec
  //    def loop(list: TailRecMyList[A], accum: Int): Int =
  //      list match {
  //        case TailRecMyPair(h, t) => loop(t, h + accum)
  //        case TailRecMyNil()      => accum
  //      }
  //
  //    loop(this, 0)
  //  }

  @tailrec
  final def exists(func: A => Boolean): Boolean =
    this match {
      case TailRecMyPair(h, t) => func(h) || t.exists(func)
      case TailRecMyNil()      => false
    }

  def filter(func: A => Boolean): TailRecMyList[A] = {
    @tailrec
    def loop(list: TailRecMyList[A], accum: TailRecMyList[A]): TailRecMyList[A] =
      list match {
        case TailRecMyPair(h, t) => loop(t, if(func(h)) TailRecMyPair(h, accum) else accum)
        case TailRecMyNil()      => accum
      }

    loop(this, TailRecMyNil()).reverse
  }

  // To make this tail recursive, we have to accumulate in reverse order and re-reverse the result:
  def reverse: TailRecMyList[A] = {
    @tailrec
    def loop(list: TailRecMyList[A], accum: TailRecMyList[A]): TailRecMyList[A] =
      list match {
        case TailRecMyPair(h, t) => loop(t, TailRecMyPair(h, accum))
        case TailRecMyNil()      => accum
      }

    loop(this, TailRecMyNil())
  }

  @tailrec
  final def find(func: A => Boolean): Option[A] =
    this match {
      case TailRecMyPair(h, t) => if(func(h)) Some(h) else t.find(func)
      case TailRecMyNil()      => None
    }

  def ++(that: TailRecMyList[A]): TailRecMyList[A] = {
    @tailrec
    def loop(list: TailRecMyList[A], accum: TailRecMyList[A]): TailRecMyList[A] =
      list match {
        case TailRecMyPair(head, tail) => loop(tail, TailRecMyPair(head, accum))
        case TailRecMyNil()            => accum
      }

    loop(that, loop(this, TailRecMyNil())).reverse
  }
}

case class TailRecMyPair[A](head: A, tail: TailRecMyList[A]) extends TailRecMyList[A]

case class TailRecMyNil[A]() extends TailRecMyList[A]

