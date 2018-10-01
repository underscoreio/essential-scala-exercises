package code

// Exercise:
//
// Write a Counter class to manage increments to a number:
//
// - Give it a constructor that takes a single number as a parameter
// - Give it a method "value" that returns the number
// - Give it a method "inc" that increases the counter by 1

class Counter(num: Int) {
  def value = num

  def inc: Counter =
    new Counter(value + 1)

  override def toString: String =
    s"Counter($value)"
}

object CounterExercise extends Exercise {
  val counter = new Counter(10)

  override def main(args: Array[String]): Unit = {
    println("INC")
    println(counter)
    println(counter.inc)
    println(counter.inc.inc)
    println(counter.inc.inc.inc)
    println(counter.inc.inc.inc.inc)
    println(counter.inc.inc.inc.inc.inc)
    println()
  }
}
