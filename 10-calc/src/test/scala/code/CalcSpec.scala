package code

import org.scalatest._

class CalcSpec extends FlatSpec with Matchers {

  "eval" should "evaluate a calculation" in {
    val calc = Add(Mul(Num(1), Num(2)), Mul(Num(3), Num(4)))

    Calc.eval(calc) should be(14)
  }

  "print" should "print a calculation" in {
    val calc = Add(Mul(Num(1), Num(2)), Mul(Num(3), Num(4)))

    Calc.print(calc) should be("1 * 2 + 3 * 4")
  }

  "print" should "insert parentheses where appropriate" in {
    val calc = Mul(Add(Num(1), Num(2)), Add(Num(3), Num(4)))

    Calc.print(calc) should be("(1 + 2) * (3 + 4)")
  }

}
