package code

// (Optional, Harder) Exercise: Design a data type for a binary tree of integers.
//
// Tip: Implement the methods below one at a time,
// checking them against the unit tests before moving on to the next
//
// Implement the following methods:
// - size — the number of nodes in the tree
// - addToEach — add a number to each node in the tree
// - sum — add together all of the nodes in the tree
// - exists
//   - takes a predicate as a parameter
//   - returns true if it applies to any node in the tree
// - filter
//   - takes a predicate as a parameter
//   - returns a tree of all nodes for which the predicate returns true
// - find
//   - takes a predicate as a parameter
//   - returns the first node (left to right) for which it returns true
// - (Optional) toList — linearise a list of all the nodes
// - (Optional) depth — the maximum distance from the root to a leaf
// - (Optional) width — number of nodes in the widest horizontal layer

sealed trait Tree[A] {
  def size: Int =
    this match {
      case Node(_, l, r) => 1 + l.size + r.size
      case Leaf()        => 0
    }

  //  def addToEach(num: Int): Tree =
  //    this match {
  //      case Node(n, l, r) => Node(n + num, l.addToEach(num), r.addToEach(num))
  //      case Leaf()        => Leaf
  //    }

  //  def sum: Int =
  //    this match {
  //      case Node(n, l, r) => n + l.sum + r.sum
  //      case Leaf()        => 0
  //    }

  def exists(func: A => Boolean): Boolean =
    this match {
      case Node(n, l, r) => func(n) || l.exists(func) || r.exists(func)
      case Leaf()        => false
    }

  def find(func: A => Boolean): Option[A] =
    this match {
      case Node(n, l, r) => Some(n).filter(func) orElse l.find(func) orElse r.find(func)
      case Leaf()        => None
    }

  def toList: List[A] =
    this match {
      case Node(n, l, r) => l.toList ++ List(n) ++ r.toList
      case Leaf()        => Nil
    }

  def depth: Int =
    this match {
      case Node(_, l, r) => 1 + math.max(l.depth, r.depth)
      case Leaf()        => 0
    }

  def width: Int = {
    val temp = widths
    if(temp.isEmpty) 0 else temp.max
  }

  def widths: List[Int] =
    this match {
      case Node(_, l, r) =>
        val lWidths = l.widths
        val rWidths = r.widths
        val padLength = math.max(lWidths.length, rWidths.length)
        val lPadded = lWidths.padTo(padLength, 0)
        val rPadded = rWidths.padTo(padLength, 0)
        1 :: lPadded.zip(rPadded).map { case (x, y) => x + y }

      case Leaf()        => Nil
    }

  def filter(func: A => Boolean): Tree[A] =
    this match {
      case Node(n, l, r) => if(func(n)) Node(n, l.filter(func), r.filter(func)) else l.filter(func) ++ r.filter(func)
      case Leaf()        => Leaf()
    }

  def ++(that: Tree[A]): Tree[A] =
    this match {
      case Node(n, l, r) => Node(n, l, r ++ that)
      case Leaf()        => that
    }
}

case class Node[A](n: A, l: Tree[A], r: Tree[A]) extends Tree[A]

case class Leaf[A]() extends Tree[A]
