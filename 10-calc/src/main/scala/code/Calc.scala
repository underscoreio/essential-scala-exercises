package code

// Design and implement:
// - An ADT Calc representing an integer expression as a data structure:
//   - support the four basic operators: +, -, *, /
//   - it should be possible to nest Calcs
// - A companion object for Calc containing:
//   - an eval method that runs a calculation
//   - a print method that prints a calculation as a friendly string

sealed trait Calc
case class Add(l: Calc, r: Calc) extends Calc
case class Sub(l: Calc, r: Calc) extends Calc
case class Mul(l: Calc, r: Calc) extends Calc
case class Div(l: Calc, r: Calc) extends Calc
case class Num(n: Int) extends Calc

object Calc {
  def eval(calc: Calc): Int =
    calc match {
      case Add(l, r) => eval(l) + eval(r)
      case Sub(l, r) => eval(l) - eval(r)
      case Mul(l, r) => eval(l) * eval(r)
      case Div(l, r) => eval(l) / eval(r)
      case Num(n)    => n
    }

  def print(calc: Calc): String =
    calc match {
      case Add(l, r) => parenthesize(l, calc) + " + " + parenthesize(r, calc)
      case Sub(l, r) => parenthesize(l, calc) + " - " + parenthesize(r, calc)
      case Mul(l, r) => parenthesize(l, calc) + " * " + parenthesize(r, calc)
      case Div(l, r) => parenthesize(l, calc) + " / " + parenthesize(r, calc)
      case Num(n)    => n.toString
    }

  def parenthesize(inner: Calc, outer: Calc): String =
    if(precedence(inner) > precedence(outer)) {
      print(inner)
    } else {
      s"(${print(inner)})"
    }

  def precedence(calc: Calc): Int =
    calc match {
      case _: Add => 1
      case _: Sub => 1
      case _: Mul => 2
      case _: Div => 2
      case _: Num => 3
    }
}
