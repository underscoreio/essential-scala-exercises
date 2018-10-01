package code

import org.scalatest._

class VecSpec extends FlatSpec with Matchers {
  // val zero = new Vec(0, 0)
  // val i = new Vec(1, 0)
  // val j = new Vec(0, 1)
  // val pythag = new Vec(3, 4)

  "length" should "return the length of a vec" in {
    pending
    // zero.length should equal(0.0)
    // i.length should equal(1.0 +- 0.001)
    // j.length should equal(1.0 +- 0.001)
    // pythag.length should equal(5.0 +- 0.001)
  }

  "* and +" should "do math on vecs" in {
    pending
    // val pythag2 = i*3 + j*4
    // pythag.x should equal(pythag2.x +- 0.001)
    // pythag.y should equal(pythag2.y +- 0.001)
  }

  "rot90" should "rotate clockwise and anticlockwise" in {
    pending
    // pythag.rot90(true) should equal(new Vec(4, -3))
    // pythag.rot90(false) should equal(new Vec(-4, 3))
  }

  "longest" should "return the longest vec" in {
    pending
    // Vec.longest(i, pythag) should equal(pythag)
    // Vec.longest(pythag, j) should equal(pythag)
    // Vec.longest(pythag * 2, pythag) should equal(pythag * 2)
  }
}
