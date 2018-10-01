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

  "get" should "find a field if it exists" in {
    val baz: JsonValue =
      JsonArray(List(
        JsonNumber(1),
        JsonNumber(2),
        JsonNumber(3),
      ))

    val bar: JsonValue =
      JsonObject(List(
        "baz" -> baz
      ))

    val foo: JsonValue =
      JsonObject(List(
        "bar" -> bar
      ))

    val top: JsonValue =
      JsonObject(List(
        "foo" -> foo
      ))

    top.get(Nil) should equal(Some(top))
    top.get(List("foo")) should equal(Some(foo))
    top.get(List("foo", "bar")) should equal(Some(bar))
    top.get(List("foo", "bar", "baz")) should equal(Some(baz))

    top.get(List("bar")) should equal(None)
  }
}
