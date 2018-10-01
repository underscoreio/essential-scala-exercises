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


sealed trait Shape
// etc...