package code

// Exercise 1:
//
// - Add a method to Vec that returns its length
//   Tip: you can take the square root a number with math.sqrt(num)
//
// - (Optional) Can you improve the printing situation?
//
// - (Optional, Harder) Can you improve the equality situation?
//   Tip: You'll need pattern matching with type ascription patterns



// Exercise 2:
//
// - Write a * operator to scale a Vec by a Double amount
//
// - Write a + operator to add two Vecs
//
// - (Optional, Harder) Write a method called rot90
//     that rotates the Vec by 90 degrees leaving its length the same:
//   - accept a Boolean parameter "cw"
//   - rotate the Vec 90 degrees clockwise if cw == true
//   - rotate the Vec 90 degrees anticlockwise if cw == false



// Exercise 3:
//
// Define a companion object for Vec:
// - Give it a value `zero` containing a 0,0 Vec
//
// - Give it a method `apply` that:
//   - accepts two Double parameters, x and y
//   - creates a new Vec from the parameters
//
// - Give it a method `longest` that
//   - accepts two parameters of type Vec
//   - returns the Vec with the largest length



class Vec(val x: Double, val y: Double) {
  def length: Double =
    math.sqrt(x * x + y * y)

  def *(scale: Double): CaseClassVec =
    new CaseClassVec(x * scale, y * scale)

  def +(that: CaseClassVec): CaseClassVec =
    new CaseClassVec(this.x + that.x, this.y + that.y)

  def rot90(cw: Boolean): CaseClassVec =
    if(cw) new CaseClassVec(y, -x) else new CaseClassVec(-y, x)

  override def toString: String =
    s"Vec($x, $y)"

  override def equals(that: Any): Boolean =
    that match {
      case that: CaseClassVec =>
        this.x == that.x && this.y == that.y

      case _ =>
        false
    }
}

object Vec {
  val zero: CaseClassVec =
    new CaseClassVec(0, 0)

  def apply(x: Double, y: Double): CaseClassVec =
    new CaseClassVec(x, y)

  def longest(a: CaseClassVec, b: CaseClassVec): CaseClassVec =
    if(a.length >= b.length) a else b
}



object VecExercise extends Exercise {
  val vec1  = new CaseClassVec(1, 2)
  val vec1b = new CaseClassVec(1, 2)
  val vec2  = new CaseClassVec(3, 4)

  override def main(args: Array[String]): Unit = {
    println("VEC")
    println(vec1)
    println(vec2)
    println()

    println("VEC EQUALITY")
    println(vec1 == vec1b)
    println(vec1 == vec2)
    println()

    println("MULTIPLICATION")
    println(vec1 * 2)
    println(vec2 * 3)
    println()

    println("ADDITION")
    println(vec1 + vec2)
    println(vec2 + vec2)
    println()

    println("ROTATION")
    println(vec1.rot90(true))
    println(vec1.rot90(false))
    println()

    println("LONGEST")
    println(CaseClassVec.longest(vec1, vec2))
    println(CaseClassVec.longest(vec1 * 5, vec2))
    println()
  }
}
