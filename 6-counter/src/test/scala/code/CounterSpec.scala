package code

import org.scalatest._

class CounterSpec extends FlatSpec with Matchers {
  "inc and get" should "inc and get" in {
    val counter = new Counter(10)
    counter.inc.value should equal(11)
    counter.inc.inc.value should equal(12)
    counter.inc.inc.inc.value should equal(13)
  }
}
