package code

// Exercise 1:
//
// Design and implement:
// - An ADT Calc representing an integer expression as a data structure:
//   - support the four basic operators: +, -, *, /
//   - it should be possible to nest Calcs
// - A companion object for Calc containing:
//   - an eval method that runs a calculation
//   - a print method that prints a calculation as a friendly string


// Exercise 2 (to be completed later):
//
// Implement a method safeEval in the companion object for Calc that
// - returns an Either[String, Int]
// - guards against division-by-zero errors
// - includes the numerator and denominator
//   in the error message in the event of failure


sealed trait Calc
// etc...