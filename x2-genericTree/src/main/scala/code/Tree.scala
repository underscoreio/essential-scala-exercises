package code

// (Optional, Harder) Exercise: Make IntTree generic
//
// The code below is a copy of IntTree, renamed to Tree
//
// Make Tree generic:
// - Introduce a type parameter A
// - Look for any rogue Int parameters that should be As
// - if any methods clearly won't transfer across, comment them out


sealed trait Tree {
  def size: Int =
    this match {
      case Node(_, l, r) => 1 + l.size + r.size
      case Leaf          => 0
    }

  def addToEach(num: Int): Tree =
    this match {
      case Node(n, l, r) => Node(n + num, l.addToEach(num), r.addToEach(num))
      case Leaf          => Leaf
    }

  def sum: Int =
    this match {
      case Node(n, l, r) => n + l.sum + r.sum
      case Leaf          => 0
    }

  def exists(func: Int => Boolean): Boolean =
    this match {
      case Node(n, l, r) => func(n) || l.exists(func) || r.exists(func)
      case Leaf          => false
    }

  def find(func: Int => Boolean): Option[Int] =
    this match {
      case Node(n, l, r) => Some(n).filter(func) orElse l.find(func) orElse r.find(func)
      case Leaf          => None
    }

  def toList: List[Int] =
    this match {
      case Node(n, l, r) => l.toList ++ List(n) ++ r.toList
      case Leaf          => Nil
    }

  def filter(func: Int => Boolean): Tree =
    this match {
      case Node(n, l, r) => if(func(n)) Node(n, l.filter(func), r.filter(func)) else l.filter(func) ++ r.filter(func)
      case Leaf          => Leaf
    }

  def ++(that: Tree): Tree =
    this match {
      case Node(n, l, r) => Node(n, l, r ++ that)
      case Leaf          => that
    }
}

case class Node(n: Int, l: Tree, r: Tree) extends Tree

case object Leaf extends Tree