package code

// Exercise 1:
//
// - Write a Scala definition for Shape
// - Also define Circle and Rect

// Exercise 2 (to be completed later):
//
// - Implement an area method using pattern matching (match on this):
// - Implement a perimeter method using inheritance
// - Implement a compare method in the companion object that
//   - accepts two shapes x and y as a parameters
//   - returns true iff x comes before y according to the following order:
//   - circles always come before rectangles
//   - circles are ordered by radius
//   - rectangles are ordered by width
// - Implement a scale method using pattern matching (match on this):
//   - accept a Double scaling factor as a parameter
//   - return a new shape, scaled up or down by the specified amount

import scala.math.Pi

sealed trait Shape {
  def area: Double =
    this match {
      case Circle(radius)      => Pi * radius * radius
      case Rect(width, height) => width * height
    }

  def perimeter: Double

  def scale(factor: Double): Shape =
    this match {
      case Circle(radius)      => Circle(radius * factor)
      case Rect(width, height) => Rect(width * factor, height * factor)
    }
}

case class Circle(radius: Double) extends Shape {
  override def perimeter: Double =
    2 * Pi * radius
}

case class Rect(width: Double, height: Double) extends Shape {
  override def perimeter: Double =
    2 * width + 2 * height
}

object Shape {
  def compare(x: Shape, y: Shape): Boolean =
    x match {
      case Circle(rx) =>
        y match {
          case Circle(ry) => rx < ry
          case Rect(_, _) => true
        }

      case Rect(wx, _) =>
        y match {
          case Circle(_)   => false
          case Rect(wy, _) => wx < wy
        }
    }
}
