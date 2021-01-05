package sandbox

sealed trait Shape

case class Circle(radius: Double) extends Shape

object CovarianceTest {

  // trait List[+A]

  // 一般的に共変はOutputに用いられる
  val circles: List[Circle] = List[Circle]()
  val shapes: List[Shape] = circles

}

// 反変
object ContravariantTest {

  val shape: Shape = Circle(1.0)
  val circle: Circle =  Circle(1.0)

  val shapeWriter: JsonWriter[Shape] = new JsonWriter[Shape] {
    override def write(value: Shape): Json = JsNull
  }
  val circleWriter: JsonWriter[Circle] = new JsonWriter[Circle] {
    override def write(value: Circle): Json = JsNumber(value.radius)
  }

  def format[A](value: A, writer: JsonWriter[A]): Json =
    writer.write(value)

}
