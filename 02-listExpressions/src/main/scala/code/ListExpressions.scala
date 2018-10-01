package code

// Exercise:
//
// Using only the following methods of List:
//   List[A].head   => A
//   List[A].tail   => List[A]
//   List[A].length => Int
//   List[A].++(List[A]) => List[A]
//
// Write the following methods:
//
// - sum
//   - accepts no parameters
//   - sums all the numbers in the list
//
// - contains
//   - accepts a List[Int] and an Int value
//   - returns true if the value appears in the list
//
// - (Harder) containsAll
//   - accepts two Lists[Int] x and y
//   - returns true if all the elements of y appear in x
//
// - (Harder still) squareNumbers
//   - accepts an Int max as a parameter
//   - returns a list of the square numbers from 1 to max inclusive
//
// Tip: a square number is the result of
// multiplying another number by itself...
// 1*1, 2*2, 3*3, ...


object ListExpressions extends Exercise {

  def sum(nums: List[Int]): Int =
    if(nums.length == 0) {
      0
    } else {
      nums.head + sum(nums.tail)
    }

  def contains(l: List[Int], n: Int): Boolean =
    if(l.length == 0) {
      false
    } else {
      l.head == n || contains(l.tail, n)
    }

  def containsAll(x: List[Int], y: List[Int]): Boolean =
    if(y.length == 0) {
      true
    } else {
      contains(x, y.head) && containsAll(x, y.tail)
    }

  def squareNumbers(max: Int): List[Int] = {
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
