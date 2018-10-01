package code

// Exercise 1:
//
// The code below is a copy of IntList, renamed to MyList.
//
// Make MyList generic in the type of data it holds:
// - give it a type parameter A
// - look for any erroneous Ints in the codebase and replace them with As
// - if any methods clearly don't transfer across, comment them out
// - do whatever else you have to to make it compile


// Exercise 2 (for later on):
//
// - Make MyList covariant
// - Add a ++ method to append two lists


// Exercise 3 (for even later on):
//
// - Implement foldLeft and foldRight for MyList
// - Reimplement everything else in terms of them!
// - (Optional, Very Hard) Implement foldRight in terms of foldLeft,
//   making it stack safe


// Exercise 4 (you can't believe how later on this is going to be):
//
// - Implement map on MyList
//   - For bonus points implement it in terms of foldRight
// - Can we use map to implement addToEach from IntList? Yes!
// - Reimplement add using map


// Exercise 4 (just, like, so late... don't even):
//
// - Implement map on MyList
//   - For bonus points implement it in terms of foldRight
// - Can we use map to implement addToEach from IntList? Yes!
// - Reimplement add using map


sealed trait MyList {
  def length: Int =
    this match {
      case MyPair(_, t) => 1 + t.length
      case MyNil        => 0
    }

  def contains(item: Int): Boolean =
    this match {
      case MyPair(h, t) => h == item || t.contains(item)
      case MyNil        => false
    }

  def addToEach(num: Int): MyList =
    this match {
      case MyPair(h, t) => MyPair(h + num, t.addToEach(num))
      case MyNil        => MyNil
    }

  def sum: Int =
    this match {
      case MyPair(h, t) => h + t.sum
      case MyNil        => 0
    }

  def exists(func: Int => Boolean): Boolean =
    this match {
      case MyPair(h, t) => func(h) || t.exists(func)
      case MyNil        => false
    }

  def filter(func: Int => Boolean): MyList =
    this match {
      case MyPair(h, t) =>
        if(func(h)) MyPair(h, t.filter(func)) else t.filter(func)
      case MyNil        => MyNil
    }

  def reverse: MyList = {
    def loop(list: MyList, accum: MyList): MyList =
      list match {
        case MyPair(h, t) => loop(t, MyPair(h, accum))
        case MyNil        => accum
      }

    loop(this, MyNil)
  }

  def find(func: Int => Boolean): Option[Int] =
    this match {
      case MyPair(h, t) => if(func(h)) Some(h) else t.find(func)
      case MyNil        => None
    }
}

case class MyPair(head: Int, tail: MyList) extends MyList

case object MyNil extends MyList
