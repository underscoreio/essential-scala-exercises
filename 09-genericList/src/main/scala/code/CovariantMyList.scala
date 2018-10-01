package code

// Covariant version of the invariant implementation in MyList

sealed trait CovariantMyList[+A] {
  def length: Int =
    this match {
      case CovariantMyPair(_, t) => 1 + t.length
      case CovariantMyNil        => 0
    }

  def contains[B >: A](item: B): Boolean =
    this match {
      case CovariantMyPair(h, t) => h == item || t.contains(item)
      case CovariantMyNil        => false
    }

  def exists[B >: A](func: B => Boolean): Boolean =
    this match {
      case CovariantMyPair(h, t) => func(h) || t.exists(func)
      case CovariantMyNil        => false
    }

  def filter[B >: A](func: B => Boolean): CovariantMyList[A] =
    this match {
      case CovariantMyPair(h, t) =>
        if(func(h)) CovariantMyPair(h, t.filter(func)) else t.filter(func)
      case CovariantMyNil        => CovariantMyNil
    }

  def reverse: CovariantMyList[A] = {
    def loop(list: CovariantMyList[A], accum: CovariantMyList[A]): CovariantMyList[A] =
      list match {
        case CovariantMyPair(h, t) => loop(t, CovariantMyPair(h, accum))
        case CovariantMyNil        => accum
      }

    loop(this, CovariantMyNil)
  }

  def find[B >: A](func: B => Boolean): Option[B] =
    this match {
      case CovariantMyPair(h, t) => if(func(h)) Some(h) else t.find(func)
      case CovariantMyNil        => None
    }

  def ++[B >: A](that: CovariantMyList[B]): CovariantMyList[B] =
    this match {
      case CovariantMyPair(head, tail) => CovariantMyPair(head, tail ++ that)
      case CovariantMyNil              => that
    }

  def map[B](func: A => B): CovariantMyList[B] =
    this match {
      case CovariantMyPair(head, tail) => CovariantMyPair(func(head), tail.map(func))
      case CovariantMyNil              => CovariantMyNil
    }

  def flatMap[B](func: A => CovariantMyList[B]): CovariantMyList[B] =
    this match {
      case CovariantMyPair(head, tail) => func(head) ++ tail.flatMap(func)
      case CovariantMyNil              => CovariantMyNil
    }
}

case class CovariantMyPair[A](head: A, tail: CovariantMyList[A]) extends CovariantMyList[A]
case object CovariantMyNil extends CovariantMyList[Nothing]
