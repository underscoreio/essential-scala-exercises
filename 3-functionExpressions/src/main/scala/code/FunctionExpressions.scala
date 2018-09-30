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

  def now: Long =
    System.currentTimeMillis

  def repeat(func: Int => Int): Int => Int =
    (arg: Int) => func(func(arg))



  // Function types

  type GreetFunc = String => String

  type NowFunc = () => Long

  // If IntelliJ says you can remove the brackets here, it's wrong:
  type RepeatFunc = (Int => Int) => Int => Int



  // Function literals

  val greetFunc: GreetFunc =
    (name: String) => "Hello " + name

  val nowFunc: NowFunc =
    () => System.currentTimeMillis

  val repeatFunc: RepeatFunc =
    (func: Int => Int) => (arg: Int) => func(func(arg))



  // Lifting methods to functions

  val greetFunc2: GreetFunc =
    greet

  val nowFunc2: NowFunc =
    now _

  val repeatFunc2: RepeatFunc =
    repeat

}
