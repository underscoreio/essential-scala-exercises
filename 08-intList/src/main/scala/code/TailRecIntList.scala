package code

import scala.annotation.tailrec

// Tail recursive version of IntList

sealed trait TailRecIntList {
  def length: Int = {
    @tailrec
    def loop(list: TailRecIntList, accum: Int): Int =
      list match {
        case TailRecIntPair(_, t) => loop(t, accum + 1)
        case TailRecIntNil        => accum
      }

    loop(this, 0)
  }

  @tailrec
  final def contains(item: Int): Boolean =
    this match {
      case TailRecIntPair(h, t) => h == item || t.contains(item)
      case TailRecIntNil        => false
    }

  // To make this tail recursive, we have to accumulate in reverse order and re-reverse the result:
  def addToEach(num: Int): TailRecIntList = {
    @tailrec
    def loop(list: TailRecIntList, accum: TailRecIntList): TailRecIntList =
      list match {
        case TailRecIntPair(h, t) => loop(t, TailRecIntPair(h + num, accum))
        case TailRecIntNil        => accum
      }

    loop(this, TailRecIntNil).reverse
  }

  def sum: Int = {
    @tailrec
    def loop(list: TailRecIntList, accum: Int): Int =
      list match {
        case TailRecIntPair(h, t) => loop(t, h + accum)
        case TailRecIntNil        => accum
      }

    loop(this, 0)
  }

  @tailrec
  final def exists(func: Int => Boolean): Boolean =
    this match {
      case TailRecIntPair(h, t) => func(h) || t.exists(func)
      case TailRecIntNil        => false
    }

  def filter(func: Int => Boolean): TailRecIntList = {
    @tailrec
    def loop(list: TailRecIntList, accum: TailRecIntList): TailRecIntList =
      list match {
        case TailRecIntPair(h, t) => loop(t, if(func(h)) TailRecIntPair(h, accum) else accum)
        case TailRecIntNil        => accum
      }

    loop(this, TailRecIntNil).reverse
  }

  // To make this tail recursive, we have to accumulate in reverse order and re-reverse the result:
  def reverse: TailRecIntList = {
    @tailrec
    def loop(list: TailRecIntList, accum: TailRecIntList): TailRecIntList =
      list match {
        case TailRecIntPair(h, t) => loop(t, TailRecIntPair(h, accum))
        case TailRecIntNil        => accum
      }

    loop(this, TailRecIntNil)
  }

  @tailrec
  final def find(func: Int => Boolean): Option[Int] =
    this match {
      case TailRecIntPair(h, t) => if(func(h)) Some(h) else t.find(func)
      case TailRecIntNil        => None
    }
}

case class TailRecIntPair(head: Int, tail: TailRecIntList) extends TailRecIntList

case object TailRecIntNil extends TailRecIntList
