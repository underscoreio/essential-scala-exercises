package code

object FunctionExpressions extends Exercise {

  // Rewrite these methods using function syntax:
  //
  // 1. define the relevant function types below using type aliases
  //
  // 2. write function equivalents of each method
  //    using anonymous function syntax
  //
  // 3. as an alternative to redefining the methods as functions,
  //    try to "lift" them to functions by assigning them
  //    to a variable of the relevant function type

  def greet(name: String): String =
    "Hello " + name

  // Function type
  type GreetFunc = String => String

  // Function literal
  val greetFunc: GreetFunc =
    (name: String) => "Hello " + name

  // Lift the method to a function
  val greetFunc2: GreetFunc =
    greet



  def now: Long =
    System.currentTimeMillis

  // Function type
  type NowFunc = () => Long

  // Function literal
  // Function literals must take one parenthesis list
  val nowFunc: NowFunc =
  () => System.currentTimeMillis

  // Lift the method to a function
  // We need _ here because the method has no parameter lists
  val nowFunc2: NowFunc =
  now _




  def repeat(func: Int => Int): Int => Int =
    (arg: Int) => func(func(arg))

  // Function type
  // If IntelliJ says you can remove the brackets here, it's wrong:
  type RepeatFunc = (Int => Int) => Int => Int

  // Function literal
  val repeatFunc: RepeatFunc =
    (func: Int => Int) => (arg: Int) => func(func(arg))

  // Lift the method to a function
  val repeatFunc2: RepeatFunc =
    repeat



  override def main(args: Array[String]): Unit = {
    println("GREET")
    println(greet("Dave"))
    println(greetFunc("Dave"))
    println(greetFunc2("Dave"))
    println()

    println("NOW")
    println(now)
    println(nowFunc)
    println(nowFunc2)
    println(nowFunc())
    println(nowFunc2())
    println()

    println("REPEAT")
    println(repeat(n => n * n)(10))
    println(repeatFunc(n => n * n)(10))
    println(repeatFunc2(n => n * n)(10))
    println()
  }
}
