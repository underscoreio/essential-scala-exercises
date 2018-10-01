package code

import org.scalatest._

class CalcSpec extends FlatSpec with Matchers {

  "eval" should "evaluate a calculation" in {
    pending
    // val calc = Add(Mul(Num(1), Num(2)), Mul(Num(3), Num(4)))

    // Calc.eval(calc) should be(14)
  }

  "print" should "print a calculation" in {
    pending
    // val calc = Add(Mul(Num(1), Num(2)), Mul(Num(3), Num(4)))

    // Calc.print(calc) should be("1 * 2 + 3 * 4")
  }

  "print" should "insert parentheses where appropriate" in {
    pending
    // val calc = Mul(Add(Num(1), Num(2)), Add(Num(3), Num(4)))

    // Calc.print(calc) should be("(1 + 2) * (3 + 4)")
  }

  "safeEval" should "evaluate a valid calculation" in {
    pending
    // val calc = Add(Mul(Num(1), Num(2)), Mul(Num(3), Num(4)))

    // Calc.safeEval(calc) should be(Right(14))
  }

  it should "evaluate a valid division" in {
    pending
    // val calc = Div(Num(6), Num(2))

    // Calc.safeEval(calc) should be(Right(3))
  }

  it should "fail on a division by zero" in {
    pending
    // val calc = Div(Num(6), Num(0))

    // Calc.safeEval(calc) should be(Left("Attempt to divide 6 by 0"))
  }

}
