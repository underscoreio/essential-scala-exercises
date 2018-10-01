package code

// Version of Tree that's covariant in its type parameter


sealed trait CovariantTree[+A] {
  def size: Int =
    this match {
      case CovariantNode(_, l, r) => 1 + l.size + r.size
      case CovariantLeaf()        => 0
    }

  //  def addToEach(num: Int): CovariantTree =
  //    this match {
  //      case CovariantNode(n, l, r) => CovariantNode(n + num, l.addToEach(num), r.addToEach(num))
  //      case CovariantLeaf()        => CovariantLeaf
  //    }

  //  def sum: Int =
  //    this match {
  //      case CovariantNode(n, l, r) => n + l.sum + r.sum
  //      case CovariantLeaf()        => 0
  //    }

  def exists(func: A => Boolean): Boolean =
    this match {
      case CovariantNode(n, l, r) => func(n) || l.exists(func) || r.exists(func)
      case CovariantLeaf()        => false
    }

  def find(func: A => Boolean): Option[A] =
    this match {
      case CovariantNode(n, l, r) => Some(n).filter(func) orElse l.find(func) orElse r.find(func)
      case CovariantLeaf()        => None
    }

  def toList: List[A] =
    this match {
      case CovariantNode(n, l, r) => l.toList ++ List(n) ++ r.toList
      case CovariantLeaf()        => Nil
    }

  def depth: Int =
    this match {
      case CovariantNode(_, l, r) => 1 + math.max(l.depth, r.depth)
      case CovariantLeaf()        => 0
    }

  def width: Int = {
    val temp = widths
    if(temp.isEmpty) 0 else temp.max
  }

  def widths: List[Int] =
    this match {
      case CovariantNode(_, l, r) =>
        val lWidths = l.widths
        val rWidths = r.widths
        val padLength = math.max(lWidths.length, rWidths.length)
        val lPadded = lWidths.padTo(padLength, 0)
        val rPadded = rWidths.padTo(padLength, 0)
        1 :: lPadded.zip(rPadded).map { case (x, y) => x + y }

      case CovariantLeaf()        => Nil
    }

  def filter(func: A => Boolean): CovariantTree[A] =
    this match {
      case CovariantNode(n, l, r) => if(func(n)) CovariantNode(n, l.filter(func), r.filter(func)) else l.filter(func) ++ r.filter(func)
      case CovariantLeaf()        => CovariantLeaf()
    }

  def ++[B >: A](that: CovariantTree[B]): CovariantTree[B] =
    this match {
      case CovariantNode(n, l, r) => CovariantNode(n, l, r ++ that)
      case CovariantLeaf()        => that
    }
}

case class CovariantNode[A](n: A, l: CovariantTree[A], r: CovariantTree[A]) extends CovariantTree[A]

case class CovariantLeaf[A]() extends CovariantTree[A]
