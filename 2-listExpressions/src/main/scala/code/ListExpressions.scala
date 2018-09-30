package code

object ListExpressions extends Exercise {

  // Using only the following methods of List:
  //   List[A].head   => A
  //   List[A].tail   => List[A]
  //   List[A].length => Int
  //   List[A].++(List[A]) => List[A]

  // Write a method to sum a list of numbers
  def sum(nums: List[Int]): Int =
    if(nums.length == 0) {
      0
    } else {
      nums.head + sum(nums.tail)
    }

  // Write a method that:
  // - accepts a list of numbers l and a number n as parameters
  // - returns true if n appears within l, false otherwise
  def contains(l: List[Int], n: Int): Boolean =
    if(l.length == 0) {
      false
    } else {
      l.head == n || contains(l.tail, n)
    }

  // (Harder) Write a method that:
  // - accepts two lists of numbers x and y
  // - returns true every number in y appears in x
  def containsAll(x: List[Int], y: List[Int]): Boolean =
    if(y.length == 0) {
      true
    } else {
      contains(x, y.head) && containsAll(x, y.tail)
    }

  // (Harder still) Write a method that:
  // - accepts an Int max as a parameter
  // - returns a list of the square numbers from 1 to max inclusive
  //
  // Tip: a square number is the result of
  // multiplying another number by itself...
  // 1*1, 2*2, 3*3, ...
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
