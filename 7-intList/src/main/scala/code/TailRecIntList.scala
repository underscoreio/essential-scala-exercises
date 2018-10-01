package code

import scala.annotation.tailrec

// Exercise:
//
// Implement your list of integers as a class called IntList!
//
// Tip: Implement the methods below one at a time,
// checking them against the unit tests before moving on to the next
//
// Tip: Don't worry about making your methods tail recursive
//
// Implement the following methods:
//
// - length
//    - takes no parameters
//    - returns the length of the list
//
//  - contains
//    - takes an Int parameter
//    - checks whether the Int is in the list
//
//  - addToEach
//    - takes an Int parameter n
//    - adds n to every element in the list, returning a new list
//
//  - sum
//    - takes no parameters
//    - adds up all the elements in the list
//
//  - exists
//    - takes a predicate as a parameter
//    - returns true if the predicate applies to any item in the list
//
//  - filter
//    - takes a predicate as a parameter
//    - returns a list of all items for which the predicate returns true
//
//  - (Optional) reverse
//    - takes no parameters
//    - reverses the list
//
//  - (Optional) find
//    - takes a predicate as a parameter
//    - returns the first item for which it returns true
//    - Note:
//      - how can you handle the case where we can't find the relevant item?
//      - either use an Option to wrap the result, or introduce your own IntOption ADT
//
//  - (Optional, Harder) — make as many of your methods tail recursive as possible

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

