package code

import code.ListExpressions.{contains, containsAll, squareNumbers, sum}

import scala.annotation.tailrec

object TailrecListExpressions extends Exercise {

  // sum definitely requires alteration:

  def sum(nums: List[Int]): Int = {
    @tailrec
    def loop(nums: List[Int], accum: Int): Int =
      if(nums.length == 0) {
        accum
      } else {
        loop(nums.tail, nums.head + accum)
      }

    loop(nums, 0)
  }

  println("SUM")
  println(sum(List(1, 2, 3)))
  println()

  // contains and containsAll are already tail recursive:

  @tailrec
  def contains(l: List[Int], n: Int): Boolean =
    if(l.length == 0) {
      false
    } else {
      l.head == n || contains(l.tail, n)
    }

  println("CONTAINS")
  println(contains(List(1, 2, 3), 2))
  println(contains(List(1, 2, 3), 4))
  println()

  @tailrec
  def containsAll(x: List[Int], y: List[Int]): Boolean =
    if(y.length == 0) {
      true
    } else {
      contains(x, y.head) && containsAll(x, y.tail)
    }

  println("CONTAINS ALL")
  println(containsAll(List(1, 2, 3), List(2, 3)))
  println(containsAll(List(1, 2, 3), List(4, 3)))
  println()

  // You'll probably have used a helper method for squareNumbers
  // that may or may not require rewriting:

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

  println("SQUARE NUMBERS")
  println(squareNumbers(10))
  println(squareNumbers(20))
  println()

}
