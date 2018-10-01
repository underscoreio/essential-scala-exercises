package code

import scala.annotation.tailrec

object PatternMatchListExpressions extends Exercise {

  def sum(nums: List[Int]): Int = {
    @tailrec
    def loop(nums: List[Int], accum: Int): Int =
      nums match {
        case hd :: tl => loop(tl, hd + accum)
        case Nil      => accum
      }

    loop(nums, 0)
  }

  @tailrec
  def contains(l: List[Int], n: Int): Boolean =
    l match {
      case hd :: tl => if(hd == n) true else contains(tl, n)
      case Nil      => false
    }

  @tailrec
  def containsAll(x: List[Int], y: List[Int]): Boolean =
    y match {
      case hd :: tl => contains(x, hd) && containsAll(x, tl)
      case Nil      => true
    }

  // This one doesn't require pattern matching
  // because it's using inequality comparisons:

  def squareNumbers(max: Int): List[Int] = {
    @tailrec
    def loop(n: Int, accum: List[Int]): List[Int] = {
      val sq = n*n
      if(sq <= max) {
        loop(n + 1, sq :: accum)
      } else {
        accum
      }
    }

    loop(1, Nil)
  }

  def factorial(n: Int): Int = {
    @tailrec
    def loop(n: Int, accum: Int): Int =
      n match {
        case 1 => accum
        case n => loop(n - 1, n * accum)
      }

    loop(n, 1)
  }

  override def main(args: Array[String]): Unit = {
    println("SUM")
    println(sum(List(1, 2, 3)))
    println()

    println("CONTAINS")
    println(contains(List(1, 2, 3), 2))
    println(contains(List(1, 2, 3), 4))
    println()

    println("CONTAINS ALL")
    println(containsAll(List(1, 2, 3), List(2, 3)))
    println(containsAll(List(1, 2, 3), List(4, 3)))
    println()

    println("SQUARE NUMBERS")
    println(squareNumbers(10))
    println(squareNumbers(20))
    println()
  }
}
