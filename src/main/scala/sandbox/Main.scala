package sandbox

import cats.instances.string._
import cats.syntax.semigroup._
import sandbox.training1.{Cat, Json, Person, Printable}

object Main extends App {
  import sandbox.training1.JsonWriterInstances._
  import sandbox.training1.JsonSyntax._
  import sandbox.training1.PrinterInstances._
  import sandbox.training1.PrintableSyntax._

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
  // use syntax
  Cat("Mike", 5, "Brown").toPrint

}
