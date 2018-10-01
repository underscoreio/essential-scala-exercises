package code

// Exercise 1:
//
// Make MyList generic:
// - give it a type parameter A
// - look for Ints in the codebase and replace them with As
// - if any methods clearly don't transfer across, comment them out
//
//
// Exercise 2 (to be done later):
//
// - Make MyList covariant
// - Add a ++ method to append two lists

sealed trait MyList[A] {
  def length: Int =
    this match {
      case MyPair(_, t) => 1 + t.length
      case MyNil()      => 0
    }

  def contains(item: A): Boolean =
    this match {
      case MyPair(h, t) => h == item || t.contains(item)
      case MyNil()      => false
    }

  // This method doesn't transfer because it requires us to know about Ints and addition:
  //  def addToEach(num: Int): MyList[A] =
  //    this match {
  //      case MyPair(h, t) => MyPair(h + num, t.addToEach(num))
  //      case MyNil()      => MyNil()
  //    }

  // This method doesn't transfer because it requires us to know about Ints and addition:
  //  def sum: Int =
  //    this match {
  //      case MyPair(h, t) => h + t.sum
  //      case MyNil()      => 0
  //    }

  def exists(func: A => Boolean): Boolean =
    this match {
      case MyPair(h, t) => func(h) || t.exists(func)
      case MyNil()      => false
    }

  def filter(func: A => Boolean): MyList[A] =
    this match {
      case MyPair(h, t) =>
        if(func(h)) MyPair(h, t.filter(func)) else t.filter(func)
      case MyNil()      => MyNil()
    }

  def reverse: MyList[A] = {
    def loop(list: MyList[A], accum: MyList[A]): MyList[A] =
      list match {
        case MyPair(h, t) => loop(t, MyPair(h, accum))
        case MyNil()      => accum
      }

    loop(this, MyNil())
  }

  def find(func: A => Boolean): Option[A] =
    this match {
      case MyPair(h, t) => if(func(h)) Some(h) else t.find(func)
      case MyNil()      => None
    }

  def ++(that: MyList[A]): MyList[A] =
    this match {
      case MyPair(head, tail) => MyPair(head, tail ++ that)
      case MyNil()            => that
    }
}

case class MyPair[A](head: A, tail: MyList[A]) extends MyList[A]
case class MyNil[A]() extends MyList[A]
