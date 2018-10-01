package code

import org.scalatest._

// Design and implement:
// - a JsonValue ADT representing a JSON datum
// - a Json object containing a stringify method that renders a JsonValue as a compact string

class JsonSpec extends FlatSpec with Matchers {
  "stringify" should "render a JsonValue as a string" in {
    val jsonValue: JsonValue =
      JsonObject(List(
        "foo" -> JsonString("hello"),
        "bar" -> JsonBoolean(true),
        "baz" -> JsonArray(List(
          JsonNumber(1),
          JsonNumber(2),
          JsonNumber(3),
        ))
      ))

    val jsonString =
      """{"foo":"hello","bar":true,"baz":[1.0,2.0,3.0]}"""

    Json.stringify(jsonValue) should equal(jsonString)
  }
}
