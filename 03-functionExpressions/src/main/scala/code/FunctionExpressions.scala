package code

// Exercise:
//
// Rewrite each method using function syntax:
//
// 1. define the relevant function types below using type aliases
//
// 2. write function equivalents of each method
//    using anonymous function syntax
//
// 3. as an alternative to redefining the methods as functions,
//    try to "lift" them to functions by assigning them
//    to a variable of the relevant function type

object FunctionExpressions extends Exercise {

  def greet(name: String): String =
    "Hello " + name


  def now: Long =
    System.currentTimeMillis


  def repeat(func: Int => Int): Int => Int =
    (arg: Int) => func(func(arg))


  override def main(args: Array[String]): Unit = {
    // println("GREET")
    // println(greet("Dave"))
    // println(greetFunc("Dave"))
    // println(greetFunc2("Dave"))
    // println()

    // println("NOW")
    // println(now)
    // println(nowFunc)
    // println(nowFunc2)
    // println(nowFunc())
    // println(nowFunc2())
    // println()

    // println("REPEAT")
    // println(repeat(n => n * n)(10))
    // println(repeatFunc(n => n * n)(10))
    // println(repeatFunc2(n => n * n)(10))
    // println()
  }
}
