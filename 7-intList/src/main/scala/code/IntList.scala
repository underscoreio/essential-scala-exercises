package code

// Exercise: Implement your list of integers as a class called IntList!
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

sealed trait IntList {
  def length: Int =
    this match {
      case IntPair(_, t) => 1 + t.length
      case IntNil        => 0
    }

  def contains(item: Int): Boolean =
    this match {
      case IntPair(h, t) => h == item || t.contains(item)
      case IntNil        => false
    }

  def addToEach(num: Int): IntList =
    this match {
      case IntPair(h, t) => IntPair(h + num, t.addToEach(num))
      case IntNil        => IntNil
    }

  def sum: Int =
    this match {
      case IntPair(h, t) => h + t.sum
      case IntNil        => 0
    }

  def exists(func: Int => Boolean): Boolean =
    this match {
      case IntPair(h, t) => func(h) || t.exists(func)
      case IntNil        => false
    }

  def filter(func: Int => Boolean): IntList =
    this match {
      case IntPair(h, t) =>
        if(func(h)) IntPair(h, t.filter(func)) else t.filter(func)
      case IntNil        => IntNil
    }

  def reverse: IntList = {
    def loop(list: IntList, accum: IntList): IntList =
      list match {
        case IntPair(h, t) => loop(t, IntPair(h, accum))
        case IntNil        => accum
      }

    loop(this, IntNil)
  }

  def find(func: Int => Boolean): Option[Int] =
    this match {
      case IntPair(h, t) => if(func(h)) Some(h) else t.find(func)
      case IntNil        => None
    }
}

case class IntPair(head: Int, tail: IntList) extends IntList

case object IntNil extends IntList

