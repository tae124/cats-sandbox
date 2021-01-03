package sandbox

import cats.instances.string._
import cats.syntax.semigroup._

object Main extends App {
  import JsonWriterInstances._
  import JsonSyntax._
  import PrinterInstances._

  println("Hello " |+| "Cats!")

  val json1 = Json.toJson(Person("Dave", "dave@example.com"))
  val json2 = Person("Steve", "steve@example.com").toJson

  println(json1)
  println(json2)

  // 下記は曖昧なのでWorkしない
  /*
  implicit val writer1: JsonWriter[String] =
    JsonWriterInstances.stringWriter

  implicit val writer2: JsonWriter[String] =
    JsonWriterInstances.stringWriter

  Json.toJson("A string")
  */

  // implicit valueと衝突する変数名は利用できない
  val intPrinter = Printable.format(1234)
  val stringPrinter = Printable.format("cats")

  println(intPrinter)
  println(stringPrinter)

  // print cat!
  Printable.print(Cat("Mike", 5, "Brown"))

}
