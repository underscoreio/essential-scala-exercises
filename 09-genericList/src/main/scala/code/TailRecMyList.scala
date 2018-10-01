package code

import scala.annotation.tailrec

// A variant of CovariantMyList written entirely tail recursively.
// This is for reference only.
// I don't recommend you do it yourself.
//
// A much more convenient way of achieving stack safety
// is to make foldLeft and foldRight stack safe
// and implement everything else in terms of them.
// See FoldedMyList for an example.

sealed trait TailRecMyList[+A] {
  def length: Int = {
    @tailrec
    def loop(list: TailRecMyList[A], accum: Int): Int =
      list match {
        case TailRecMyPair(_, t) => loop(t, accum + 1)
        case TailRecMyNil        => accum
      }

    loop(this, 0)
  }

  @tailrec
  final def contains[B >: A](item: B): Boolean =
    this match {
      case TailRecMyPair(h, t) => h == item || t.contains(item)
      case TailRecMyNil        => false
    }

  @tailrec
  final def exists(func: A => Boolean): Boolean =
    this match {
      case TailRecMyPair(h, t) => func(h) || t.exists(func)
      case TailRecMyNil        => false
    }

  def filter(func: A => Boolean): TailRecMyList[A] = {
    @tailrec
    def loop(list: TailRecMyList[A], accum: TailRecMyList[A]): TailRecMyList[A] =
      list match {
        case TailRecMyPair(h, t) => loop(t, if(func(h)) TailRecMyPair(h, accum) else accum)
        case TailRecMyNil        => accum
      }

    loop(this, TailRecMyNil).reverse
  }

  // To make this tail recursive, we have to accumulate in reverse order and re-reverse the result:
  def reverse: TailRecMyList[A] = {
    @tailrec
    def loop(list: TailRecMyList[A], accum: TailRecMyList[A]): TailRecMyList[A] =
      list match {
        case TailRecMyPair(h, t) => loop(t, TailRecMyPair(h, accum))
        case TailRecMyNil        => accum
      }

    loop(this, TailRecMyNil)
  }

  @tailrec
  final def find(func: A => Boolean): Option[A] =
    this match {
      case TailRecMyPair(h, t) => if(func(h)) Some(h) else t.find(func)
      case TailRecMyNil        => None
    }

  def ++[B >: A](that: TailRecMyList[B]): TailRecMyList[B] = {
    @tailrec
    def loop(list: TailRecMyList[B], accum: TailRecMyList[B]): TailRecMyList[B] =
      list match {
        case TailRecMyPair(head, tail) => loop(tail, TailRecMyPair(head, accum))
        case TailRecMyNil              => accum
      }

    loop(that, loop(this, TailRecMyNil)).reverse
  }

  def map[B](func: A => B): TailRecMyList[B] = {
    @tailrec
    def loop(list: TailRecMyList[A], accum: TailRecMyList[B]): TailRecMyList[B] =
      list match {
        case TailRecMyPair(head, tail) => loop(tail, TailRecMyPair(func(head), accum))
        case TailRecMyNil              => accum
      }

    loop(this, TailRecMyNil).reverse
  }

  def flatMap[B](func: A => TailRecMyList[B]): TailRecMyList[B] = {
    @tailrec
    def loop(list: TailRecMyList[A], accum: TailRecMyList[B]): TailRecMyList[B] =
      list match {
        case TailRecMyPair(head, tail) => loop(tail, func(head).reverse ++ accum)
        case TailRecMyNil              => accum
      }

    loop(this, TailRecMyNil).reverse
  }
}

case class TailRecMyPair[A](head: A, tail: TailRecMyList[A]) extends TailRecMyList[A]

case object TailRecMyNil extends TailRecMyList[Nothing]

