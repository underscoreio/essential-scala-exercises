package code

import org.scalatest._
import scala.math.Pi

class ShapeSpec extends FlatSpec with Matchers {
  "area" should "calculate the area of a shape" in {
    pending
    // Circle(5).area should equal(25 * Pi +- 0.001)
    // Rect(3, 4).area should equal(12.0 +- 0.001)
  }

  "perimeter" should "calculate the perimeter of a shape" in {
    pending
    // Circle(5).perimeter should equal(10 * Pi +- 0.001)
    // Rect(3, 4).perimeter should equal(14.0 +- 0.001)
  }

  "scale" should "scale a shape up" in {
    pending
    // Circle(3).scale(2) should equal(Circle(6))
    // Rect(3, 4).scale(2) should equal(Rect(6, 8))
  }

  it should "scale a shape down" in {
    pending
    // Circle(6).scale(.5) should equal(Circle(3))
    // Rect(6, 8).scale(.5) should equal(Rect(3, 4))
  }

  "compare" should "compare shapes" in {
    pending
    // val c1 = Circle(1)
    // val c2 = Circle(2)
    // val r1 = Rect(1, 2)
    // val r2 = Rect(2, 1)

    // Shape.compare(c1, c2) should equal(true)
    // Shape.compare(c2, c1) should equal(false)

    // Shape.compare(c1, r1) should equal(true)
    // Shape.compare(r1, c1) should equal(false)

    // Shape.compare(c1, r2) should equal(true)
    // Shape.compare(r2, c1) should equal(false)

    // Shape.compare(r1, r2) should equal(true)
    // Shape.compare(r2, r1) should equal(false)
  }
}
