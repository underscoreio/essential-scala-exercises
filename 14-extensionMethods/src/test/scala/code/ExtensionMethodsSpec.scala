package code

import org.scalatest._

class ExtensionMethodsSpec extends FlatSpec with Matchers {
  import ExtensionMethods._

  "hello" should "give a friendly greeting" in {
    "Dave".hello should equal("Hello Dave")
  }

  "times" should "produce a nice list" in {
    3.times(n => s"It's the number $n!") should equal(List(
      "It's the number 1!",
      "It's the number 2!",
      "It's the number 3!",
    ))
  }

}
