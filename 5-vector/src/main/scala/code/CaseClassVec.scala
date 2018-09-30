package code

// Vec after refactoring to a case class:
// - we don't need to write "val" on the constructor parameters
// - we don't need to write "new" any more
// - we don't need to define "toString", "equals", or "apply" any more
// - we get these additional features for free:
//   - copy method
//   - hashCode method
//   - extractor pattern for use in pattern matching

case class CcVec(x: Double, y: Double) {
  def length: Double =
    math.sqrt(x * x + y * y)

  def *(scale: Double): CcVec =
    CcVec(x * scale, y * scale)

  def +(that: CcVec): CcVec =
    CcVec(this.x + that.x, this.y + that.y)

  def rot90(cw: Boolean): CcVec =
    if(cw) new CcVec(y, -x) else new CcVec(-y, x)
}

object CcVec {
  val zero: CcVec =
    new CcVec(0, 0)

  def longest(a: CcVec, b: CcVec): CcVec =
    if(a.length >= b.length) a else b
}



object CcVecExercise extends Exercise {
  val vec1  = CcVec(1, 2)
  val vec1b = CcVec(1, 2)
  val vec2  = CcVec(3, 4)

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
    println(CcVec.longest(vec1, vec2))
    println(CcVec.longest(vec1 * 5, vec2))
    println()
  }
}
