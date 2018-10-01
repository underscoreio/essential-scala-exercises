package code

// Exercise:
//
// - create an implicit class HelloOps that adds this method to String:
//   - hello
//     - accepts a String name as a parameter
//     - returns a greeting for name
//
// - create an implicit class TimesOps that adds this method to Int:
//   - times
//     - accepts a function from Int to A as a parameter
//     - calls the function N times, collecting the results into a list

object ExtensionMethods extends Exercise {
  implicit class HelloOps(name: String) {
    def hello: String =
      s"Hello $name"
  }

  implicit class TimesOps(num: Int) {
    def times[A](func: Int => A): List[A] =
      (1 to num).map(func).toList
  }

  println("HELLO")
  println("Dave".hello)
  println()

  println("TIMES")
  println(3.times(n => s"It's the number $n!"))
  println()
}
